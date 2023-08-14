package com.neueda.muskaan1;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.exception.CustomerAlreadyExists;
import com.neueda.muskaan1.repo.ICustomerRepository;
import com.neueda.muskaan1.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    

}
