package com.neueda.muskaan1.controller;

import com.neueda.muskaan1.entity.Transactions;
import com.neueda.muskaan1.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin

public class TransactionController {
    @Autowired
   private  TransactionService transactionService;
    @GetMapping("/merchant/{customerMerchant}")
    public ResponseEntity<List<Transactions>> getCustomerByMerchant(@PathVariable String customerMerchant) {
        List<Transactions> customerList = transactionService.getSpendingHistoryByMerchant(customerMerchant);
        return ResponseEntity.ok(customerList);
    }
    @GetMapping("/city/{customerCity}")
    public ResponseEntity<List<Transactions>> getCustomerByCity(@PathVariable String customerCity) {
        List<Transactions> customerList = transactionService.getSpendingHistoryByCity(customerCity);
        return ResponseEntity.ok(customerList);
    }
    @GetMapping("/state/{transState}")
    public ResponseEntity<List<Transactions>> getCustomerByState(@PathVariable String transactionState) {
        List<Transactions> customerList = transactionService.getSpendingHistoryByState(transactionState);
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{transNum}")
    public List<Transactions> getAllByTransactionNumAsc() {
       return transactionService.geAllByTransactionNumAsc();

    }
}
