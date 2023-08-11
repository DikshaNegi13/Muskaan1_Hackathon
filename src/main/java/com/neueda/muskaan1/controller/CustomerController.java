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
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            Customer addedCustomer = customerService.addCustomer(customer);
            return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);
        } catch (CustomerAlreadyExists e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customer_id) {
        try {
            Customer customer = customerService.getCustomerById(customer_id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (CustomerNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{customer_id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customer_id, @RequestBody Customer customer) {
        // Logic for updating customer
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{customer_id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customer_id) {
        try {
            customerService.deleteCustomer(customer_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
