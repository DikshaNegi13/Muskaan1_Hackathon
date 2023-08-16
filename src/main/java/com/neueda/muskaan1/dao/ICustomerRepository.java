package com.neueda.muskaan1.dao;
import com.neueda.muskaan1.entity.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends MongoRepository<Customer, ObjectId> {

      boolean existsByCustomerId(int customerId);

//   Optional<Customer> findByCustomerId(String customerId);

    List<Customer> findByFirstName(String customerName);
    List<Customer> findByLastName(String customerLastName);
    @Query("{customerId:?0}")
    public Customer findByCustomerId(int customerId);


    List<Customer> findByJob(String customerJob);

    List<Customer> findByGender(String customerGender);

//    boolean existsById(Long customerId);
}
