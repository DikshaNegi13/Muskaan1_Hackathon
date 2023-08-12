package com.neueda.muskaan1.repo;
import com.neueda.muskaan1.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ICustomerRepository extends MongoRepository<Customer,String> {

}
