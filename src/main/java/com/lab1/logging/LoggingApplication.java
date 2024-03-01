package com.lab1.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "application.properties","logging.properties" })
public class LoggingApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoggingApplication.class, args);
    }
}
