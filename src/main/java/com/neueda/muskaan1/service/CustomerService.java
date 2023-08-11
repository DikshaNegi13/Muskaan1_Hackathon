
package com.neueda.muskaan1.service;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.exception.CustomerAlreadyExists;
import com.neueda.muskaan1.exception.CustomerNotFound;
import com.neueda.muskaan1.repo.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository repo;

    public Customer addCustomer(Customer c) throws CustomerAlreadyExists {
        if (repo.existsById(c.getCustomer_id())) {
            throw new CustomerAlreadyExists("Customer with ID " + c.getCustomer_id() + " already exists");
        }
        return repo.save(c);
    }

    public List<Customer> getAllCustomer() {
        return repo.findAll();
    }

    public Customer getCustomerById(String id) throws CustomerNotFound {
        Optional<Customer> optionalCustomer = repo.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new CustomerNotFound("Customer with ID " + id + " not found");
        }
    }

    public void updateCustomer(Customer dataToUpdate) {
        if (repo.existsById(dataToUpdate.getCustomer_id())) {
            repo.save(dataToUpdate);
        } else {
            System.out.println("None found.");
        }
    }

    public void deleteCustomer(String id) throws CustomerNotFound {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new CustomerNotFound("Customer with ID " + id + " not found");
        }
    }
}
