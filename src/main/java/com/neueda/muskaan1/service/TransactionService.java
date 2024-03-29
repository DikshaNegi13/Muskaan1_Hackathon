package com.neueda.muskaan1.service;

import com.neueda.muskaan1.dao.TransactionMongoTemplate;
import com.neueda.muskaan1.dto.*;
import com.neueda.muskaan1.entity.Transactions;
import com.neueda.muskaan1.dao.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

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

    public List<JobAmount> getSpendingHistoryByJob() {
        return dao.getSpendingHistoryByJob();
    }


    public List<GenderAmount> getSpendingHistoryByGender() {
        return dao.getSpendingHistoryByGender();
    }

    public List<CityAmount> getSpendingHistoryByCity() {
        return dao.getSpendingHistoryByCity();
    }

    public List<StateAmount> getSpendingHistoryByState() {
        return dao.getSpendingHistoryByState();
    }

    public List<CityPopulation> getSpendingHistoryByPopulation() {
       return dao.getSpendingHistoryByPopulation();
    }


    public List<AmountSpending> getSpendingHistoryByAmount(double low,double high) {
        return dao.getSpendingHistoryByAmount(low,high);
    }

    public List<TopMerchant> getTopMerchants(int limit) {
        return dao.getTopMerchants(limit);
    }

}