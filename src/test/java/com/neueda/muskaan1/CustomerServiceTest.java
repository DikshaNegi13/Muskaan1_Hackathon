package com.neueda.muskaan1;

import com.neueda.muskaan1.dao.ICustomerRepository;
import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.exception.CustomerAlreadyExists;
import com.neueda.muskaan1.exception.CustomerNotFound;
import com.neueda.muskaan1.service.CustomerService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
class CustomerServiceTest {

    @Mock
    private ICustomerRepository repo;

    @InjectMocks
    private CustomerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCustomer_Success() throws CustomerAlreadyExists {
        Customer customer = new Customer();
        customer.setCustomerId(1001);
        customer.setFirstName("John");
        customer.setLastName("Cole");

        when(repo.existsByCustomerId(1001)).thenReturn(false);
        when(repo.save(any(Customer.class))).thenReturn(customer);

        Customer result = service.addCustomer(customer);

        assertEquals(1001, result.getCustomerId());
        assertEquals("John", result.getFirstName());
        assertEquals("Cole", result.getLastName());

        verify(repo, times(1)).existsByCustomerId(1001);
        verify(repo, times(1)).save(any(Customer.class));
    }

    @Test
    void testAddCustomer_CustomerAlreadyExists() {
        Customer customer = new Customer();
        customer.setCustomerId(1002);
        customer.setFirstName("John");
        customer.setLastName("Cole");

        when(repo.existsByCustomerId(1002)).thenReturn(true);

        assertThrows(CustomerAlreadyExists.class, () -> service.addCustomer(customer));

        verify(repo, times(1)).existsByCustomerId(1002);
        verify(repo, never()).save(any(Customer.class));
    }

    @Test
    void testGetAllCustomer() {
        List<Customer> customerList = Collections.singletonList(new Customer());
        when(repo.findAll()).thenReturn(customerList);

        List<Customer> result = service.getAllCustomer();

        assertEquals(customerList, result);
        verify(repo, times(1)).findAll();
    }

    @Test
    void testUpdateCustomer_Success() {
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(1002);
        existingCustomer.setFirstName("John");
        existingCustomer.setLastName("Doe");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName("Jane");
        updatedCustomer.setLastName("Smith");

        when(repo.findByCustomerId(1002)).thenReturn(existingCustomer);
        when(repo.save(any(Customer.class))).thenReturn(updatedCustomer);

        Customer result = service.updateCustomer(1002, updatedCustomer);

        assertEquals("Jane", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals(1002, result.getCustomerId());

        verify(repo, times(1)).findByCustomerId(1002);
        verify(repo, times(1)).save(any(Customer.class));
    }

    @Test
    void testUpdateCustomer_CustomerNotFound() {
        when(repo.findByCustomerId(1002)).thenReturn(null);

        assertThrows(CustomerNotFound.class, () -> service.updateCustomer(1002, new Customer()));

        verify(repo, times(1)).findByCustomerId(1002);
        verify(repo, never()).save(any(Customer.class));
    }

    @Test
    void testDeleteCustomer_Success() {
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(1002);
        existingCustomer.setFirstName("John");
        existingCustomer.setLastName("Doe");

        when(repo.findByCustomerId(1002)).thenReturn(existingCustomer);

        service.deleteCustomer(1002);

        verify(repo, times(1)).findByCustomerId(1002);
        verify(repo, times(1)).delete(existingCustomer);
    }

    @Test
    void testDeleteCustomer_CustomerNotFound() {
        when(repo.findByCustomerId(1002)).thenReturn(null);

        assertThrows(CustomerNotFound.class, () -> service.deleteCustomer(1002));

        verify(repo, times(1)).findByCustomerId(1002);
        verify(repo, never()).delete(any(Customer.class));
    }

    @Test
    void testGetCustomerById_Success() {
        Customer customer = new Customer();
        customer.setCustomerId(1002);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        when(repo.findByCustomerId(1002)).thenReturn(customer);

        Customer result = service.getCustomerById(1002);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals(1002, result.getCustomerId());

        verify(repo, times(1)).findByCustomerId(1002);
    }

    @Test
    void testGetCustomerById_CustomerNotFound() {
        when(repo.findByCustomerId(1002)).thenReturn(null);

        assertThrows(CustomerNotFound.class, () -> service.getCustomerById(1002));

        verify(repo, times(1)).findByCustomerId(1002);
    }



}
