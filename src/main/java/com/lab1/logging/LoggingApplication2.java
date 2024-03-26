package com.lab1.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "second.properties","application.properties" })
public class LoggingApplication2 {
    public static void main(String[] args) {
        System.setProperty("server.port", "8084");
        SpringApplication.run(LoggingApplication2.class, args);
    }
}
