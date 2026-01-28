package com.example.datadrivenbackendjava.service;

import com.example.datadrivenbackendjava.model.Transaction;
import com.example.datadrivenbackendjava.model.TransactionType;
import com.example.datadrivenbackendjava.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService (TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction save (Transaction transaction) {
        return repository.save(transaction);
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public List<Transaction> findByType(TransactionType type) {
        return repository.findByType(type);
    }
}
