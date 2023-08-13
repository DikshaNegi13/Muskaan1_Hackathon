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
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
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
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable String customerId) throws CustomerNotFound {
        Optional<Customer> customerList = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerList);
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
    @GetMapping("/gender/{customerGender}")
    public ResponseEntity <List<Customer>> getCustomerByGender(@PathVariable String customerGender) {
        List<Customer> customerList = customerService.getCustomerByGender(customerGender);
        return ResponseEntity.ok(customerList);
    }
    @GetMapping("/job/{customerJob}")
    public ResponseEntity <List<Customer>> getCustomerByJob(@PathVariable String customerJob) {
        List<Customer> customerList = customerService.getCustomerByJob(customerJob);
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
