package com.example.datadrivenbackendjava.repository;

import com.example.datadrivenbackendjava.model.Transaction;
import com.example.datadrivenbackendjava.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByType(TransactionType type);
}
