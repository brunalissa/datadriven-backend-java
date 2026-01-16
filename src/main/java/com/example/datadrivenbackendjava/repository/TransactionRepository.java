package com.example.datadrivenbackendjava.repository;

import com.example.datadrivenbackendjava.model.Transaction;
import com.example.datadrivenbackendjava.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    TransactionRepository findByType(TransactionType type);
}
