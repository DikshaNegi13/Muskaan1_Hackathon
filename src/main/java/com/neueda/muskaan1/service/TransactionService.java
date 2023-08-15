package com.neueda.muskaan1.service;

import com.neueda.muskaan1.dao.TransactionMongoTemplate;
import com.neueda.muskaan1.dto.CategoryAmount;
import com.neueda.muskaan1.dto.GenderAmount;
import com.neueda.muskaan1.dto.MerchantAmount;
import com.neueda.muskaan1.dto.StateAmount;
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

    public List<MerchantAmount> getMerchantAmount() {
        return dao.getAmountForMerchant();
    }
    public List<CategoryAmount> getSpendingHistoryByCategory() {
        return dao.getSpendingHistoryByCategory();
    }
    public List<GenderAmount> getSpendingHistoryByGender() {
        return dao.getSpendingHistoryByGender();
    }
    public List<StateAmount> getSpendingHistoryByState() {
        return dao.getSpendingHistoryByState();

    }

    public List<Transactions> getSpendingHistoryByCity(String city) {
        System.out.println(city + " Printing from service class");
        List<Transactions> customerList = transactionRepo.findByCity(city);
        return customerList;
    }



}