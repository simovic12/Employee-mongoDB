package com.example.employee.mongoDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmployeeMongoDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMongoDBApplication.class, args);
	}

}
