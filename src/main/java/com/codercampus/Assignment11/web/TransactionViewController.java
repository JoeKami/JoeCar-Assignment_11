package com.codercampus.Assignment11.web;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transactions")
public class TransactionViewController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping
    public String getAllTransactions(ModelMap model) {
        List<Transaction> allTransactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", allTransactions);
        return "transactions";
    }

    @GetMapping("/{transactionId}")
    public String getTransactionById(@PathVariable("transactionId") Long transactionId, ModelMap model) {
        Optional<Transaction> transaction = transactionService.getTransactionById(transactionId);
        if (transaction.isPresent()) {
            model.addAttribute("transaction", transaction.get());
            return "transaction" + transactionId;
        } else {
            return "transactionNotFound";
        }
    }
}