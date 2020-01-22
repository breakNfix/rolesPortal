package com.example.rolesPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.springboot.controllers"})
public class RolesPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RolesPortalApplication.class, args);
	}

}
