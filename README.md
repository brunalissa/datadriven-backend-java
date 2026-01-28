## Data-Driven Backend API – Java, Spring Boot & PostgreSQL

## *Project Description*

This project is a RESTful backend API built with *Java and Spring Boot*, designed to manage and query financial transactions in a structured, data-driven way.

The application follows a clean layered architecture, separating concerns between controllers, services, repositories, and domain models. It emphasizes maintainability, strong typing, and explicit business rules.

## *Objectives*

The API provides the following functionalities:

Create financial transactions

Retrieve all stored transactions

Filter transactions by type

Enforce business rules in a centralized service layer

Persist data using JPA with PostgreSQL

Expose interactive API documentation via Swagger

## *Technologies Used*

Java 17

Spring Boot

Spring Web (REST APIs)

Spring Data JPA

PostgreSQL

H2 Database (for local/testing profile)

Swagger / OpenAPI

Maven

Git

## *Architecture Overview*

- The project follows a layered architecture:

Controller → Service → Repository → Database

- Responsibilities:

Controller: Exposes REST endpoints

Service: Contains business logic and validations

Repository: Handles persistence with JPA

Model: Domain entities and enums

DTO: API data transfer objects

## *Domain Model*
- Transaction

Represents a financial transaction.

Field	Type	Description
id	Long	Unique identifier
description	String	Transaction description
amount	BigDecimal	Monetary value
type	TransactionType	Transaction category
TransactionType (Enum)

- Allowed transaction types:

INCOME
EXPENSE


The enum is persisted as a String using @Enumerated(EnumType.STRING).

## *Business Rules*

A transaction must contain:

A non-empty description

A valid monetary amount

A valid transaction type

Only predefined TransactionType values are accepted

Type conversion and validation occur in the service layer

Controllers never access repositories directly

## *Running the Project Locally*
- Prerequisites

Java 17+

Maven

PostgreSQL

PostgreSQL Configuration

Create a database:

CREATE DATABASE transactions_db;


Update application.yml or application.properties:

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/transactions_db
    username: postgres
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.PostgreSQLDialect

Run the Application
mvn spring-boot:run


The API will be available at:

http://localhost:8080

## *Swagger / OpenAPI Documentation*

Swagger UI is enabled for API exploration and testing.

Access it at:

http://localhost:8080/swagger-ui.html


or

http://localhost:8080/swagger-ui/index.html

🔗 Main Endpoints
Create a transaction
POST /transactions


Request Body:

{
  "description": "Monthly salary",
  "amount": 5000,
  "type": "INCOME"
}

Retrieve all transactions
GET /transactions

Retrieve transactions by type
GET /transactions/type/{type}


Example:

GET /transactions/type/EXPENSE

## *Example Requests*
Create a transaction (cURL)
curl -X POST http://localhost:8080/transactions \
-H "Content-Type: application/json" \
-d '{
  "description": "Groceries",
  "amount": 150,
  "type": "EXPENSE"
}'

Retrieve all transactions
curl http://localhost:8080/transactions

## *## Recent Fixes*
- Fixed application startup issues related to JPA configuration
- Corrected entity mappings and repository definitions
- Improved application stability during context initialization


## *Future Improvements*

Bean Validation (@NotNull, @Positive)

Global exception handling

Automated tests

Pagination and sorting

Docker support

CI/CD pipeline

## *Final Notes*

This project demonstrates backend fundamentals, clean architecture, and explicit business rules, serving as a foundation for scalable backend and data-oriented applications.
