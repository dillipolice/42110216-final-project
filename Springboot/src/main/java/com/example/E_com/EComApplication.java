package com.example.E_com; // Package declaration for the application

import org.springframework.boot.SpringApplication; // Import for running the Spring Boot app
import org.springframework.boot.autoconfigure.SpringBootApplication; // Import for enabling Spring Boot features

@SpringBootApplication // Enables auto-configuration, component scanning, and configuration
public class EComApplication {

	public static void main(String[] args) { // Main method to start the application
		extracted(args);
	}

	private static void extracted(String[] args) {
		SpringApplication.run(EComApplication.class, args); // Launches the Spring Boot application
	}
}