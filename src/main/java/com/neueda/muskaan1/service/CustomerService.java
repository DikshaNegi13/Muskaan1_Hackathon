package com.neueda.muskaan1.service;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.exception.*;
import com.neueda.muskaan1.repo.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository repo;
    //methods

    public Customer addCustomer(Customer c) throws CustomerAlreadyExists {
        if(repo.existsById(c.getCustomerId())) throw new CustomerAlreadyExists("Customer with "+ c.getCustomerId()+" already exists");
        Customer savedEntity=this.repo.save(c);
        return savedEntity;
    }
    //    public long getCount(){
//        return this.repo.count();
//    }
    public List<Customer>getAllCustomer(){
        return this.repo.findAll();
    }
    public Customer updateCustomer(String customerId, Customer dataToUpdate) {
        if (repo.existsById(dataToUpdate.getCustomerId()))
        {
            repo.save((dataToUpdate));
        }
        else
            System.out.println("Customer not found");
        return dataToUpdate;
    }
    public void deleteCustomer(String customerId){
        Customer c =repo.findById(customerId).orElse(null);
        if(c!=null){
            repo.save(c);
        }
        else
            System.out.println("not found");
    }

    public Customer getCustomerById(String customerId) throws CustomerNotFound {
        System.out.println(customerId+"Printing from service class");
        Customer customer = repo.findByCustomerId(customerId).orElse(null);
        System.out.println(customer+"Printing from customer service class");
        if (customer == null) {
            throw new CustomerNotFound("Customer with ID " + customerId + " not found");
       }
        return customer;
    }
    public List<Customer> getCustomerByName(String customerName)  {
        System.out.println(customerName+"Printing from service class");
        List<Customer> customerList = repo.findByFirstName(customerName);
        return customerList;
    }
    public List<Customer> getCustomerByLastName(String customerLastName)  {
        System.out.println(customerLastName+"Printing from service class");
        List<Customer> customerList = repo.findByLastName(customerLastName);
        return customerList;
    }



}