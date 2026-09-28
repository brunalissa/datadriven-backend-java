package com.example.datadrivenbackendjava;

import com.example.datadrivenbackendjava.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionApiTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TransactionRepository repository;
    private final HttpClient client = HttpClient.newHttpClient();

    @BeforeEach
    void clearDatabase() {
        repository.deleteAll();
    }

    private HttpResponse<String> post(String body) throws Exception {
        return client.send(HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/transaction"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body)).build(), HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> get(String path) throws Exception {
        return client.send(HttpRequest.newBuilder(URI.create("http://localhost:" + port + path)).build(),
                HttpResponse.BodyHandlers.ofString());
    }

    @Test
    void createAndListPreserveTypeAndCategory() throws Exception {
        var response = post("""
                {"description":"Salary","amount":1200.50,"category":"Work","type":"INCOME"}
                """);
        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.body()).contains("\"category\":\"Work\"", "\"type\":\"INCOME\"");
        var listed = get("/transaction/type/income");
        assertThat(listed.statusCode()).isEqualTo(200);
        assertThat(listed.body()).contains("\"category\":\"Work\"", "\"type\":\"INCOME\"");
        assertThat(repository.count()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "{}",
            "{\"description\":\"Expense\",\"amount\":-1,\"category\":\"Food\",\"type\":\"EXPENSE\"}",
            "{\"description\":\"Expense\",\"amount\":0,\"category\":\"Food\",\"type\":\"EXPENSE\"}",
            "{\"description\":\" \",\"amount\":1,\"category\":\"Food\",\"type\":\"EXPENSE\"}",
            "{\"description\":\"Expense\",\"amount\":1,\"category\":\"Food\"}",
            "{\"description\":\"Expense\",\"amount\":1,\"category\":\"Food\",\"type\":\"INVALID\"}"
    })
    void invalidTransactionsAreRejectedWithoutPersistence(String body) throws Exception {
        assertThat(post(body).statusCode()).isEqualTo(400);
        assertThat(repository.count()).isZero();
    }

    @Test
    void invalidFilterReturnsBadRequest() throws Exception {
        assertThat(get("/transaction/type/INVALID").statusCode()).isEqualTo(400);
    }
}
