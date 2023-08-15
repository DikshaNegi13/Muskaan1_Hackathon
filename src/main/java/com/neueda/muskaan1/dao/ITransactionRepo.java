package com.neueda.muskaan1.dao;

import com.neueda.muskaan1.entity.Transactions;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ITransactionRepo extends MongoRepository<Transactions, ObjectId> {

    List<Transactions> findByCity(String city);








}
