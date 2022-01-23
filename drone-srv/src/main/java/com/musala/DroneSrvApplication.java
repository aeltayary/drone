package com.musala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class DroneSrvApplication {
	public static void main(String[] args) {
		SpringApplication.run(DroneSrvApplication.class, args);
	}
	
}
