package com.bo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages= {"com.bo"})
public class BlueOceanApp {

	public static void main(String[] args) {
		SpringApplication.run(BlueOceanApp.class, args);
	}
	
	
	
}
