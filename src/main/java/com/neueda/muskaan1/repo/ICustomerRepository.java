package com.neueda.muskaan1.repo;
import com.neueda.muskaan1.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends MongoRepository<Customer,String> {

    boolean existsById(String customerId);

//   Optional<Customer> findByCustomerId(String customerId);

    List<Customer> findByFirstName(String customerName);
    List<Customer> findByLastName(String customerLastName);
    @Query("{customerId:'?0'}")
    Optional<Customer> findById(String customerId);

    List<Customer> findByJob(String customerJob);

    List<Customer> findByGender(String customerGender);

    //boolean existsById(Long customerId);
}
