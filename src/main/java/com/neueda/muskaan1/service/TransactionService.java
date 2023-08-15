package com.neueda.muskaan1.service;

import com.neueda.muskaan1.dao.TransactionMongoTemplate;
import com.neueda.muskaan1.dto.CategoryAmount;
import com.neueda.muskaan1.dto.MerchantAmount;
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
    public List<Transactions> getSpendingHistoryByGender(String gender) {
        return dao.getSpendingHistoryByGender(gender);
    }
    public List<Transactions> getProfessionOfCustomer(String Job)
    {
        return dao.getProfessionOfCustomer(Job);
    }
    public List<Transactions> getPopulationForCity(long city_population)
    {
        return dao.getPopulationForCity(city_population);
    }
    public List<Transactions> getSpendingHistoryByState(String state) {
        System.out.println(state + " Printing from service class");
        return dao.getSpendingHistoryByState(state);

    }

    public List<Transactions> getSpendingHistoryByCity(String city) {
        System.out.println(city + " Printing from service class");
        List<Transactions> customerList = transactionRepo.findByCity(city);
        return customerList;
    }


}