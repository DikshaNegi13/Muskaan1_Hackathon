package com.neueda.muskaan1.repo;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.entity.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ITransactionRepo extends MongoRepository<Transactions, Transactions.Id>{
    List<Customer> findByMerchant(String merchant);

    List<Customer> findByCity(String customerCity);
}
