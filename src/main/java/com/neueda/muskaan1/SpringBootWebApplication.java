package com.neueda.muskaan1;

import com.neueda.muskaan1.entity.Customer;
import com.neueda.muskaan1.dao.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.logging.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication

public class SpringBootWebApplication implements CommandLineRunner {
	static Logger LOGGER= Logger.getLogger(SpringBootWebApplication.class.getSimpleName());

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
		LOGGER.info("Application Started");

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


	@Override
	public void run(String... args) throws Exception {

	}
}