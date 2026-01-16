package com.example.datadrivenbackendjava.controller;

import com.example.datadrivenbackendjava.model.Transaction;
import com.example.datadrivenbackendjava.model.TransactionType;
import com.example.datadrivenbackendjava.service.TransactionService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/transaction")

public class TransactionController {

    private final TransactionService service;

    public TransactionController (TransactionService service){
        this.service = service;
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return service.save(transaction);
    }

    @GetMapping
    public List<Transaction> findAll() {
        return service.findAll();
    }

    @GetMapping("/type/{type}")
    public List<Transaction> findByType(@PathVariable String type) {
        return service.findByType(TransactionType.valueOf(type)).findAll();
    }

}

