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

    if (repo.existsByCustomerId(customer.getCustomerId())) {
        throw new CustomerAlreadyExists("Customer with ID " + customer.getCustomerId() + " already exists.");
    }

    // Add the customer
    return repo.save(customer);
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
        Customer customer = repo.findByCustomerId(customerId);
        return customer;
    }

    public List<Customer> getCustomerByName(String customerName)  {
        System.out.println(customerName+ " Printing from service class");
        List<Customer> customerList = repo.findByFirstName(customerName);
        return customerList;
    }
    public List<Customer> getCustomerByGender(String customerGender)  {

        List<Customer> customerListByGender = repo.findByGender(customerGender);
        return customerListByGender;
    }
    public List<Customer> getCustomerByJob(String customerJob)  {

        List<Customer> customerListByJob = repo.findByJob(customerJob);
        return customerListByJob;
    }
    public List<Customer> getCustomerByLastName(String customerLastName)  {
        System.out.println(customerLastName + " Printing from service class");
        List<Customer> customerListByLast = repo.findByLastName(customerLastName);
        return customerListByLast;
    }

}