package com.neueda.muskaan1.controller;
import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.service.CustomerService;

import com.neueda.muskaan1.exception.CustomerAlreadyExists;
import com.neueda.muskaan1.validation.ErrorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
//import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> addCustomer( @RequestBody @Valid  Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse("Validation failed");
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorResponse.addValidationError(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            Customer savedCustomer = customerService.addCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
        } catch (CustomerAlreadyExists e) {
            ErrorResponse errorResponse = new ErrorResponse("Customer already exists");
            errorResponse.addValidationError("customerId", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer==null) {
            ErrorResponse errorResponse = new ErrorResponse("No customers found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/name/{customerName}")
    public ResponseEntity <?> getCustomerByName(@PathVariable String customerName) {
        List<Customer> customerList = customerService.getCustomerByName(customerName);
           if (customerList.isEmpty()) {
               ErrorResponse errorResponse = new ErrorResponse("No customers found");
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           }
           return ResponseEntity.ok(customerList);
        }
    @GetMapping("/lastname/{customerLastName}")
    public ResponseEntity <?> getCustomerByLastName(@PathVariable String customerLastName) {
        List<Customer> customerList = customerService.getCustomerByLastName(customerLastName);
        if (customerList.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("No customers found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
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
    public ResponseEntity<?> updateCustomer(
            @PathVariable int customerId ,@RequestBody @Valid Customer updatedCustomer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse("Validation failed");
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorResponse.addValidationError(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorResponse);
        }

        Customer updated = customerService.updateCustomer(customerId, updatedCustomer);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

}
