package com.lab1.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "application.properties","facade.properties" })
public class FacadeApplication {
	public static void main(String[] args) {
		SpringApplication.run(FacadeApplication.class, args);
	}
}
