package com.neueda.muskaan1.dao;
import com.neueda.muskaan1.entity.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ICustomerRepository extends MongoRepository<Customer, ObjectId> {

    //  boolean existsById(int customerId);

//   Optional<Customer> findByCustomerId(String customerId);

    List<Customer> findByFirstName(String customerName);
    List<Customer> findByLastName(String customerLastName);
    //@Query("{customerId:'?0'}")
    public Customer findById( int customerId);

    List<Customer> findByJob(String customerJob);

    List<Customer> findByGender(String customerGender);

    //boolean existsById(Long customerId);
}
