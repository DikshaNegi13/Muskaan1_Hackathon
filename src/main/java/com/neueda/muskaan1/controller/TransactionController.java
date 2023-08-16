package com.neueda.muskaan1.controller;

import com.neueda.muskaan1.dto.*;
import com.neueda.muskaan1.entity.Transactions;
import com.neueda.muskaan1.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value="/city", method =RequestMethod.GET)
    public List<CityAmount> getAmountForCity() {
        return transactionService.getSpendingHistoryByCity();
    }
    @RequestMapping(value = "/Job",method = RequestMethod.GET)
    public List<JobAmount> getAmountByJob()
    {
        return transactionService.getSpendingHistoryByJob();
    }
    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public List<CategoryAmount> getCustomerByCategory()
    {
        return transactionService.getSpendingHistoryByCategory();
    }
    @GetMapping("/city_population")
    public <city_population> List<Transactions> getPopulationByCity(String city)
    {

        return transactionService.getPopulationByCity(city);
    }

    @GetMapping("/{state}")
    public List<StateAmount> getCustomerByState() {
        return transactionService.getSpendingHistoryByState();
    }
    @GetMapping("/total_amt/{gender}")
    public List<GenderAmount> getCustomerByGender() {
        return transactionService.getSpendingHistoryByGender();
    }

    @GetMapping("/spendingByAmount/{low}/{high}")
    public List<AmountSpending> getSpendingByAmount(@PathVariable double low ,@PathVariable double high ) {
        return transactionService.getSpendingHistoryByAmount(low,high);
    }
    @GetMapping("/topMerchants")
    public List<TopMerchant> getTopMerchants(@RequestParam int limit) {
        return transactionService.getTopMerchants(limit);
    }


}
