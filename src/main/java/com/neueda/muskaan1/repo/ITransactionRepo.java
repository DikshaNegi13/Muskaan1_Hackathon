package com.neueda.muskaan1.repo;

import com.neueda.muskaan1.entity.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITransactionRepo extends MongoRepository<Transactions, Transactions.Id>{
}
