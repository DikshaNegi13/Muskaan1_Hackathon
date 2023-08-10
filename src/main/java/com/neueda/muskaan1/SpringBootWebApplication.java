package com.neueda.muskaan1;

import com.neueda.muskaan1.repo.ICustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
public class SpringBootWebApplication {
     static Logger LOGGER = (Logger) LoggerFactory.getLogger(SpringBootWebApplication.class.getSimpleName());
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);

		CreditCardManagement creditCardManagement = context.getBean(CreditCardManagement.class);
		System.out.println(creditCardManagement.getfirst());
		System.out.println(creditCardManagement.getCustomer_id());
		System.out.println(creditCardManagement.getCity());
		System.out.println(creditCardManagement.getTransactionData());



		CustomerService customerService = context.getBean(CustomerService.class);
		customerService.getAllCustomers();
		try {
			customerService.updateCustomer(new customer(16, "Shalini", 124000, "Cardiff"));
		} catch (RecordNotFoundException e) {
			throw new RuntimeException(e);

		}
		customerService.getAllCustomers();
	}
	@Autowired
	private ICustomerRepository repo;


}
