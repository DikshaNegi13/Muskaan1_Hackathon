package com.neueda.muskaan1.controller;

import com.neueda.muskaan1.entity.Transactions;
import com.neueda.muskaan1.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin

public class TransactionController {
    @Autowired
   private  TransactionService transactionService;
    @GetMapping("/merchant/{customerMerchant}")
    public ResponseEntity<List<Transactions>> getCustomerByMerchant(@PathVariable String customerMerchant) {
        List<Transactions> customerList = transactionService.getSpendingHistoryByMerchant(customerMerchant);
        return ResponseEntity.ok(customerList);
    }
    @GetMapping("/category/{CustomerCategory}")
    public ResponseEntity<List<Transactions>> getCustomerByCategory(@PathVariable String customerCategory) {
        List<Transactions> customerList = transactionService.getSpendingHistoryByCategory(customerCategory);
        return ResponseEntity.ok(customerList);
    }
    @GetMapping("/city/{customerCity}")
    public ResponseEntity<List<Transactions>> getCustomerByCity(@PathVariable String customerCity) {
        List<Transactions> customerList = transactionService.getSpendingHistoryByCity(customerCity);
        return ResponseEntity.ok(customerList);
    }
    @GetMapping("/transactionStatus/{state}")
    public ResponseEntity<List<Transactions>> getCustomerByState(@PathVariable String state) {
        List<Transactions> customerList = transactionService.getSpendingHistoryByState(state);
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{transNum}")
    public List<Transactions> getAllByTransactionNum() {
       return transactionService.geAllByTransactionNum();

    }
}
