package com.neueda.muskaan1.service;

import com.neueda.muskaan1.entity.Transactions;
import com.neueda.muskaan1.repo.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private ITransactionRepo transactionRepo;

    public List<Transactions> getSpendingHistoryByMerchant(String merchant) {
        System.out.println(merchant + " Printing from service class");
        List<Transactions> transactionsList = transactionRepo.findByMerchant(merchant);
        return transactionsList;
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


    public List<Transactions> geAllByTransactionNum() {

        return transactionRepo.findAllByTransactionNum();
    }
}