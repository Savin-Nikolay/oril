package com.example.oril;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrilApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrilApplication.class, args);
	}

}
