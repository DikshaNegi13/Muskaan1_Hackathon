package com.neueda.muskaan1.repo;

import com.neueda.muskaan1.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ICustomerRepository extends MongoRepository<Customer,String> {

}
