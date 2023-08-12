package com.neueda.muskaan1.controller;
import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.service.CustomerService;

import com.neueda.muskaan1.exception.CustomerAlreadyExists;
import com.neueda.muskaan1.exception.CustomerNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            Customer savedCustomer = customerService.addCustomer(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (CustomerAlreadyExists e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        try {
            System.out.println(customerId);
            Customer customer = customerService.getCustomerById(customerId);
            System.out.println(customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (CustomerNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/name/{customerName}")
    public ResponseEntity <List<Customer>> getCustomerByName(@PathVariable String customerName) {
           List<Customer> customerList = customerService.getCustomerByName(customerName);
            return ResponseEntity.ok(customerList);
        }
    @GetMapping("/lastname/{customerLastName}")
    public ResponseEntity <List<Customer>> getCustomerByLastName(@PathVariable String customerLastName) {
        List<Customer> customerList = customerService.getCustomerByLastName(customerLastName);
        return ResponseEntity.ok(customerList);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
