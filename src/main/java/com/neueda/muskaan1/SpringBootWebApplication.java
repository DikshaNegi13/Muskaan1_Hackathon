package com.neueda.muskaan1;

import com.neueda.muskaan1.repo.ICustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
public class SpringBootWebApplication implements CommandLineRunner {
	@Autowired
	ICustomerRepository customerRepository;
	static Logger logger= Logger.getLogger(SpringBootWebApplication.class.getSimpleName());

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

	}
}