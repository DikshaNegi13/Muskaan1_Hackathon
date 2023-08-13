package com.neueda.muskaan1.repo;

import com.neueda.muskaan1.entity.Transactions;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ITransactionRepo extends MongoRepository<Transactions, Id>{
    List<Transactions> findByMerchant(String merchant);

    List<Transactions> findByCity(String customerCity);

    List<Transactions> findByState(String transactionState);

    List<Transactions> findByTransactionNum(Sort transactionNum);
}
