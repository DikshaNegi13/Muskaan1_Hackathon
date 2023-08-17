package com.neueda.muskaan1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;


@SpringBootApplication

public class SpringBootWebApplication {
	static Logger LOGGER= Logger.getLogger(SpringBootWebApplication.class.getSimpleName());

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
		LOGGER.info("Application Started");

	}
}