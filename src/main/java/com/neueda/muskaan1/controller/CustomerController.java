package com.neueda.muskaan1.controller;
import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.service.CustomerService;

import com.neueda.muskaan1.exception.CustomerAlreadyExists;
import com.neueda.muskaan1.exception.CustomerNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customer}")
    public List<Customer> getAllUsers() {return customerService.getAllCustomer();
    }


    @PostMapping("/{customer}")
    public Customer addCustomer(Customer customer) {
//        if(customerService.getCustomerById(customer.getCustomer_id()))
//            throw new CustomerAlreadyExists("User with "+ customer.getCustomer_id() + " already exists!");
//        return (Customer) customerService.addCustomer(customer);
//
    try{
        return this.customerService.addCustomer(customer);}
    catch(CustomerAlreadyExists e){
        System.out.println(e.getMessage());
    }
return null;
    }

    @GetMapping("/{customer_id}")
    public String getUserById(@PathVariable String id){
//        return customerService.getCustomerById(id).orElseThrow(() -> new CustomerNotFound("User with "+ "id"+id));
//        Customer savedEntity=this.repo.save(c);
//        return savedEntity;

        try{
            return this.customerService.getCustomerById(id);}
        catch(CustomerNotFound e){
//            System.out.println(e.getMessage());
//            throw new CustomerNotFound(e.);
           return this.customerService;
        }
        return null;


    }
//     if(repo.existsById(c.getCustomer_id())) throw new CustomerAlreadyExists("Customer with "+ c.getCustomer_id()+" already exists");


    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        User existingUser = customerService.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());

            return customerService.save(existingUser);
        }
        return null;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser (@PathVariable String id)throws CustomerNotFound {
        if(!customerService.existsById(id))
            throw new CustomerNotFound("User with "+ id + " not found!");
        mongoRepository.deleteById(id);
    }
}