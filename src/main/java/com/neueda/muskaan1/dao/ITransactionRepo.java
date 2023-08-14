package com.neueda.muskaan1.dao;

import com.neueda.muskaan1.entity.Transactions;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ITransactionRepo extends MongoRepository<Transactions, ObjectId> {
    List<Transactions> findByMerchant(String merchant);

    List<Transactions> findByCity(String customerCity);

    @Aggregation(pipeline = {
            "{'$match':{'state':?0}}",
            "{'$sample'':{size:?20}}",
            "{'$sort':{'trans_num': 1}}"

    })

    List<Transactions> findByState(String state);

  //  List<Transactions> findByTransactionNum(Sort transactionNum);
@Aggregation(pipeline = {
        "{'$match:{'trans_num':?0}}",
        "{'$sample'':{size:?20}}",
        "{'$sort':{'trans_num': -1}}"
})
  //  @Query("ORDER BY transactionNum ASC")
    List<Transactions> findAllByTransactionNum();

    List<Transactions> findByCategory(String category);
}
