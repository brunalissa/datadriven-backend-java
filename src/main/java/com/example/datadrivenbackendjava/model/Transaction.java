package com.example.datadrivenbackendjava.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String description;
    private BigDecimal amount;
    private String category;

    @Enumerated(EnumType.STRING)

    private TransactionType type;
    private LocalDateTime createdAt;

    public Transaction() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String Category(String category) {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

     public void setAmount(BigDecimal amount) {
        this.amount = amount;
     }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
