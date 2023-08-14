package com.neueda.muskaan1;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.exception.CustomerAlreadyExists;
import com.neueda.muskaan1.exception.CustomerNotFound;
import com.neueda.muskaan1.repo.ICustomerRepository;
import com.neueda.muskaan1.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import static org.mockito.Mockito.when;

public class CustomerServiceTest {
    @Mock
    private ICustomerRepository repo;
    @InjectMocks
    private CustomerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCustomer_Success() throws CustomerAlreadyExists {
        Customer customer = new Customer();
        customer.setCustomerId("123");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        // Set other properties later

        when(repo.existsById("123")).thenReturn(false);
        when(repo.save(any(Customer.class))).thenReturn(customer);

        Customer result = service.addCustomer(customer);

        assertEquals("123", result.getCustomerId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        // Add assertions for other properties later

        verify(repo, times(1)).existsById("123");
        verify(repo, times(1)).save(any(Customer.class));
    }

    @Test
    void testAddCustomer_CustomerAlreadyExists() {
        Customer customer = new Customer();
        customer.setCustomerId("123");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        // Set other properties...

        when(repo.existsById("123")).thenReturn(true);

        assertThrows(CustomerAlreadyExists.class, () -> service.addCustomer(customer));

        verify(repo, times(1)).existsById("123");
        verify(repo, never()).save(any(Customer.class));
    }

    @Test
    void testGetAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        // Populate customerList with test data...

        when(repo.findAll()).thenReturn(Collections.singletonList(customerList));

        List<Customer> result = service.getAllCustomer();

        assertEquals(customerList, result);

        verify(repo, times(1)).findAll();
    }

    @Test
    void testUpdateCustomer_Success() {
        Customer customer = new Customer();
        customer.setCustomerId("123");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        // Set other properties...

        when(repo.existsById("123")).thenReturn(true);
        when(repo.save(any(Customer.class))).thenReturn(customer);

        Customer result = service.updateCustomer("123", customer);

        assertEquals("123", result.getCustomerId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        // Add assertions for other properties...

        verify(repo, times(1)).existsById("123");
        verify(repo, times(1)).save(any(Customer.class));
    }

    @Test
    void testUpdateCustomer_CustomerNotFound() {
        Customer customer = new Customer();
        customer.setCustomerId("123");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        // Set other properties...

        when(repo.existsById("123")).thenReturn(false);

        assertThrows(CustomerNotFound.class, () -> service.updateCustomer("123", customer));

        verify(repo, times(1)).existsById("123");
        verify(repo, never()).save(any(Customer.class));
    }

    @Test
    void testDeleteCustomer_Success() {
        Customer customer = new Customer();
        customer.setCustomerId("123");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        // Set other properties...

        when(repo.findById("123")).thenReturn(Optional.of(customer));

        service.deleteCustomer("123");

        verify(repo, times(1)).findById("123");
        verify(repo, times(1)).delete(customer);
    }

    @Test
    void testDeleteCustomer_CustomerNotFound() {
        when(repo.findById("123")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFound.class, () -> service.deleteCustomer("123"));

        verify(repo, times(1)).findById("123");
        verify(repo, never()).delete(any(Customer.class));
    }

    @Test
    void testGetCustomerById_Success() throws CustomerNotFound {
        Customer customer = new Customer();
        customer.setCustomerId("123");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        // Set other properties...

        when(repo.findById("123")).thenReturn(Optional.of(customer));

        Optional<Customer> result = service.getCustomerById("123");

        assertEquals("123", result.getCustomerId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }


}
