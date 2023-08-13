package com.neueda.muskaan1.repo;

import com.neueda.muskaan1.entity.Transactions;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ITransactionRepo extends MongoRepository<Transactions, Id>{
    List<Transactions> findByMerchant(String merchant);

    List<Transactions> findByCity(String customerCity);
    @Aggregation(pipeline = {
            "{'$match':{'state':?0}}",
            "{$sample':{size:10}}"
    })

    List<Transactions> findByState(String transactionState);

  //  List<Transactions> findByTransactionNum(Sort transactionNum);
@Aggregation(pipeline = {
        "{'$match:{'trans_num':?0}}",
        "{'$sort':{'Quantity': -1}}"
})
  //  @Query("ORDER BY transactionNum ASC")
    List<Transactions> findAllByTransactionNumAsc();
}
