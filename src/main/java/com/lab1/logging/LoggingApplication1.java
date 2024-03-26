package com.lab1.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "first.properties","application.properties" })
public class LoggingApplication1 {
    public static void main(String[] args) {
        System.setProperty("server.port", "8083");
        SpringApplication.run(LoggingApplication1.class, args);
    }
}
