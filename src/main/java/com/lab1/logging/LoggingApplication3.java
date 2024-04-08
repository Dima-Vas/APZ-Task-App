package com.lab1.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "third.properties","application.properties" })
public class LoggingApplication3 {
    public static void main(String[] args) {
        System.setProperty("server.port", "8088");
        System.setProperty("myown.clustername", "cluster_number_3");
        SpringApplication.run(LoggingApplication3.class, args);
    }
}
