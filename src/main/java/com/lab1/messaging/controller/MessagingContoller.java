package com.lab1.messaging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab1.messaging.service.MessagingService;


@RestController
public class MessagingContoller {
 
    @Autowired
    MessagingService messagingService;

    @GetMapping("/")
    public String getStaticMessage() {
        return messagingService.getStaticMessage();
    }
}
