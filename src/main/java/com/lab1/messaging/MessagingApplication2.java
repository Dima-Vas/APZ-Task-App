package com.lab1.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "application.properties","messaging.properties" })
public class MessagingApplication2 {
    public static void main(String[] args) {
        System.setProperty("server.port", "8089");
        System.setProperty("myown.clustername", "messagecluster");
        System.setProperty("clustername", "messagecluster");
        SpringApplication.run(MessagingApplication2.class, args);
    }
}
