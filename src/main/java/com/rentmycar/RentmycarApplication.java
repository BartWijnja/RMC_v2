package com.rentmycar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RentmycarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentmycarApplication.class, args);
	}

}
