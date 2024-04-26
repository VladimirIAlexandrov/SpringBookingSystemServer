package com.example;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
//@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@SpringBootApplication
public class EventBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventBookingApplication.class, args);
	}
}
