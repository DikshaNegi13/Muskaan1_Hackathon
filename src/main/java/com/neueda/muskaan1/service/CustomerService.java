package com.neueda.muskaan1.service;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.exception.*;
import com.neueda.muskaan1.dao.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository repo;

//    public Customer addCustomer(@Valid Customer c) throws CustomerAlreadyExists {
//        if (repo.existsById((c.getId())))
//            throw new CustomerAlreadyExists("Customer with " + c.getCustomerId() + " already exists");
//        return repo.save(c);
//        }
public Customer addCustomer(Customer customer) throws CustomerAlreadyExists {
    if (!isCustomerIdValid(String.valueOf(customer.getCustomerId()))) {
        throw new InvalidDataTypeException(String.valueOf(customer.getCustomerId()),"Invalid customerId. Please provide a valid integer value.");
    }

    if (repo.existsByCustomerId(customer.getCustomerId())) {
        throw new CustomerAlreadyExists("Customer with ID " + customer.getCustomerId() + " already exists.");
    }

    // Add the customer
    return repo.save(customer);
}

    private boolean isCustomerIdValid(String customerId) {
        try {
            int parsedCustomerId = Integer.parseInt(customerId);
            return parsedCustomerId > 0;
        } catch (NumberFormatException e) {
            return false; // Parsing failed, not a valid integer
        }
    }



    public long getCount(){
       return this.repo.count();
        }
    public List<Customer>getAllCustomer(){
        return this.repo.findAll();
    }
    public Customer updateCustomer(int customerId, Customer dataToUpdate) {
        Customer existingCustomer = repo.findByCustomerId(customerId);
        if (existingCustomer != null) {
            existingCustomer.setCustomerId(dataToUpdate.getCustomerId());
            existingCustomer.setFirstName(dataToUpdate.getFirstName());
            existingCustomer.setLastName(dataToUpdate.getLastName());
            existingCustomer.setGender(dataToUpdate.getGender());
            existingCustomer.setJob(dataToUpdate.getJob());
            existingCustomer.setDob(dataToUpdate.getDob());

            return repo.save(existingCustomer);
        } else {
            System.out.println("Customer not found");
            return null;
        }
    }

    public void deleteCustomer(int customerId){
        Customer c =repo.findByCustomerId(customerId);
        if(c!=null){
            repo.delete(c);
        }
        else
            System.out.println("not found");
    }

    public Customer getCustomerById(int  customerId)  {
        System.out.println(customerId + "Printing");
        Customer c = repo.findByCustomerId(customerId);
        return c;
    }

    public List<Customer> getCustomerByName(String customerName)  {
        System.out.println(customerName+ " Printing from service class");
        List<Customer> customerList = repo.findByFirstName(customerName);
        return customerList;
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


    public boolean existsByCustomerId(int customerId) {
        return repo.existsByCustomerId(customerId);
    }



}