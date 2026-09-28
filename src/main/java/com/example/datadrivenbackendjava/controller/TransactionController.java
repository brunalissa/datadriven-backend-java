package com.example.datadrivenbackendjava.controller;

import com.example.datadrivenbackendjava.model.Transaction;
import com.example.datadrivenbackendjava.model.TransactionType;
import com.example.datadrivenbackendjava.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;


import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/transaction")

public class TransactionController {

    private final TransactionService service;

    public TransactionController (TransactionService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@Valid @RequestBody Transaction transaction) {
        return service.save(transaction);
    }

    @GetMapping
    public List<Transaction> findAll() {
        return service.findAll();
    }

    @GetMapping("/type/{type}")
    public List<Transaction> findByType(@PathVariable String type) {
        return service.findByType(TransactionType.valueOf(type.toUpperCase(Locale.ROOT)));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail invalidType(IllegalArgumentException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "Invalid transaction type. Use INCOME or EXPENSE.");
    }

}

