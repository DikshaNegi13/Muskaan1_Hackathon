package com.neueda.muskaan1.controller;

import com.neueda.muskaan1.entity.Customer;
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
    public ResponseEntity<List<Customer>> getCustomerByMerchant(@PathVariable String customerMerchant) {
        List<Customer> customerList = transactionService.getCustomerByMerchant(customerMerchant);
        return ResponseEntity.ok(customerList);
    }
    @GetMapping("/city/{customerCity}")
    public ResponseEntity<List<Customer>> getCustomerByCity(@PathVariable String customerCity) {
        List<Customer> customerList = transactionService.getCustomerByCity(customerCity);
        return ResponseEntity.ok(customerList);
    }
}
