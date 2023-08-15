package com.neueda.muskaan1.service;

import com.neueda.muskaan1.dao.TransactionMongoTemplate;
import com.neueda.muskaan1.entity.Transactions;
import com.neueda.muskaan1.dao.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private ITransactionRepo transactionRepo;
    @Autowired
    private TransactionMongoTemplate dao;

    public List<Transactions> getSpendingHistoryByMerchant(String merchant) {
        List<Transactions> transactionsList = transactionRepo.findByMerchant(merchant);
        return transactionsList;
    }
    public List<Transactions> getSpendingHistoryByCategory(String category) {
        List<Transactions> customerList=transactionRepo.findByCategory(category);
        return customerList;
    }

    public List<Transactions> getSpendingHistoryByCity(String city) {
        System.out.println(city + " Printing from service class");
        List<Transactions> customerList = transactionRepo.findByCity(city);
        return customerList;
    }

    public List<Transactions> getSpendingHistoryByState(String state) {
        System.out.println(state + " Printing from service class");
        List<Transactions> customerList = transactionRepo.findByState(state);
        return customerList;
    }

    public List<Transactions> getSpendingHistoryByGender(String gender) {
        System.out.println(gender + " Printing from service class");
        List<Transactions> customerList = transactionRepo.findByGender(gender);
        return customerList;
    }


}