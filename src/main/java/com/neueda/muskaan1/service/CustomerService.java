package com.neueda.muskaan1.service;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.exception.CustomerAlreadyExists;
import com.neueda.muskaan1.repo.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository repo;
    //methods

    public Customer addCustomer(Customer c) throws CustomerAlreadyExists {
     if(repo.existsById(c.getCustomer_id())) throw new CustomerAlreadyExists("Customer with "+ c.getCustomer_id()+" already exists");
      Customer savedEntity=this.repo.save(c);
      return savedEntity;
    }
//    public long getCount(){
//        return this.repo.count();
//    }
    public List<Customer>getAllCustomer(){
        return this.repo.findAll();
    }
    public void updateCustomer(Customer dataToUpdate) {
        if (repo.existsById(dataToUpdate.getCustomer_id()))
        {
            repo.save((dataToUpdate));
        }
        else
            System.out.println("Customer not found");
    }
    public void deleteCustomer(long id){
        Customer c =repo.findById(String.valueOf(id)).orElse(null);
        if(c!=null){
            repo.save(c);
        }
        else
            System.out.println("not found");
    }

    public Optional<Customer> getCutomerById(String id) {
        return this.repo.findById(id);
    }


}
