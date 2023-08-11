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

    public CustomerService(ICustomerRepository repo) {
        this.repo = repo;
    }
    //methods

    public Customer addCustomer(Customer c) throws CustomerAlreadyExists {

     if(repo.existsById(c.getCustomer_id()))
         throw new CustomerAlreadyExists("Customer with "+ c.getCustomer_id()+" already exists");
        return this.repo.save(c);
    }
    //    public long getCount(){
//        return this.repo.count();
//    }
    public List<Customer>getAllCustomer(){
        return this.repo.findAll();
    }
    public Customer updateCustomer(String customer_id, Customer dataToUpdate) {
        if (repo.existsById(dataToUpdate.getCustomer_id()))
        {
            repo.save((dataToUpdate));
        }
        else
            System.out.println("Customer not found");
        return dataToUpdate;
    }
    public void deleteCustomer(String id){
        Customer c =repo.findById(id).orElse(null);
        if(c!=null){
            repo.save(c);
        }
        else
            System.out.println("not found");
    }

    public Customer getCustomerById(String id) throws CustomerNotFound {
        Customer customer = repo.findById(id).orElse(null);
        if (customer == null) {
            throw new CustomerNotFound("Customer with ID " + id + " not found");
        }
        return customer;
    }




