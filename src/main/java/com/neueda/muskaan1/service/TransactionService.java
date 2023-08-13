package com.neueda.muskaan1.service;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.repo.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private ITransactionRepo transactionRepo;

    public List<Customer> getCustomerByMerchant(String customerMerchant)  {
        System.out.println(customerMerchant + " Printing from service class");
        List<Customer> customerList = transactionRepo.findByMerchant(customerMerchant);
        return customerList;
    }
    public List<Customer> getCustomerByCity(String customerCity)  {
        System.out.println(customerCity + " Printing from service class");
        List<Customer> customerList = transactionRepo.findByCity(customerCity);
        return customerList;
    }
}
