package com.neueda.muskaan1.service;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.exception.*;
import com.neueda.muskaan1.repo.ICustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository repo;

    public Customer addCustomer(@Valid Customer c) throws CustomerAlreadyExists {
        if (repo.existsById((c.getCustomerId())))
            throw new CustomerAlreadyExists("Customer with " + c.getCustomerId() + " already exists");
        return repo.save(c);
        }



    public long getCount(){
       return this.repo.count();
        }
    public List<Customer>getAllCustomer(){
        return this.repo.findAll();
    }
    public Customer updateCustomer(String customerId, @Valid Customer dataToUpdate) {
        Customer existingCustomer = repo.findById(customerId).orElse(null);
        if (existingCustomer != null) {

            // Ensures customerId is unchanged
            dataToUpdate.setCustomerId(existingCustomer.getCustomerId());
            // It takes the original customerId

            return repo.save(dataToUpdate);
        } else {
            System.out.println("Customer not found");
            return null;
        }
    }

    public void deleteCustomer(String customerId){
        Customer c =repo.findById(customerId).orElse(null);
        if(c!=null){
            repo.save(c);
        }
        else
            System.out.println("not found");
    }

    public Optional<Customer> getCustomerById(String  customerId)  {
        System.out.println(customerId + "Printing");
        Optional<Customer> customerList = repo.findById(customerId);
        return customerList;
    }

    public List<Customer> getCustomerByName(String customerName)  {
        System.out.println(customerName+ " Printing from service class");
        return repo.findByFirstName(customerName);
    }
    public List<Customer> getCustomerByGender(String customerGender)  {

        List<Customer> customerList = repo.findByGender(customerGender);
        return customerList;
    }
    public List<Customer> getCustomerByJob(String customerJob)  {

        List<Customer> customerList = repo.findByJob(customerJob);
        return customerList;
    }
    public List<Customer> getCustomerByLastName(String customerLastName)  {
        System.out.println(customerLastName + " Printing from service class");
        List<Customer> customerList = repo.findByLastName(customerLastName);
        return customerList;
    }



}