package com.neueda.muskaan1;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.repo.ICustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.logging.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
@Validated
public class SpringBootWebApplication implements CommandLineRunner {
	static Logger logger= Logger.getLogger(SpringBootWebApplication.class.getSimpleName());

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);

	}
	@Autowired
	private ICustomerRepository customerRepository;
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE")
						.allowedOrigins("*");
			}
		};
	}
	@Bean
	public void init() {
		logger.info("CUSTOMER DATA ADDED");
		customerRepository.save(new Customer("988","Aaron","Harvey","M","Tourist information centre manager","1974-12-23"));
		customerRepository.save(new Customer("989","Ardiana","Murray","F","Tourist information centre manager","1986-11-21"));
		customerRepository.save(new Customer("990","Alicia","Colon","F","Barrister", "1978-05-10"));
		customerRepository.save(new Customer("991","John","Miller","M","Barrister", "1988-10-2000"));
	}


	@Override
	public void run(String... args) throws Exception {

	}
}