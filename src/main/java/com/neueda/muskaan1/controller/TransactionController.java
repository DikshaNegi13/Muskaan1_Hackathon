package com.neueda.muskaan1.controller;

import com.neueda.muskaan1.dto.*;
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
    public List<MerchantAmount> getAmountForMerchant() {
        return transactionService.getMerchantAmount();
    }
    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public List<CategoryAmount> getCustomerByCategory() {
        return transactionService.getSpendingHistoryByCategory();
    }
    @RequestMapping(value="/city_population",method = RequestMethod.GET)
    public <city_population> List<Transactions> getPopulationForCity()
    {
        return transactionService.getPopulationForCity();
    }
    @RequestMapping(value="/Job",method = RequestMethod.GET)
    public List<Profession> getProfessionForCustomer()
    {
        return transactionService.getProfessionForCustomer();
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
