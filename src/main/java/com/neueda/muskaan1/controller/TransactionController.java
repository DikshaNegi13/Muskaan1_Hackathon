package com.neueda.muskaan1.controller;

import com.neueda.muskaan1.dto.CategoryAmount;
import com.neueda.muskaan1.dto.GenderAmount;
import com.neueda.muskaan1.dto.MerchantAmount;
import com.neueda.muskaan1.dto.StateAmount;
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
    @RequestMapping(value="/merchant", method =RequestMethod.GET)
    public List<MerchantAmount> getMerchantAmount() {
        return transactionService.getMerchantAmount();
    }
    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public List<CategoryAmount> getCustomerByCategory() {
        return transactionService.getSpendingHistoryByCategory();
    }
    @GetMapping("/{state}")
    public List<StateAmount> getCustomerByState() {
        return transactionService.getSpendingHistoryByState();
    }
    @GetMapping("/total_amt/{gender}")
    public List<GenderAmount> getCustomerByGender() {
        return transactionService.getSpendingHistoryByGender();
    }
    @GetMapping("/city/{customerCity}")
    public ResponseEntity<List<Transactions>> getCustomerByCity(@PathVariable String customerCity) {
        List<Transactions> customerList = transactionService.getSpendingHistoryByCity(customerCity);
        return ResponseEntity.ok(customerList);
    }



}
