package com.lab1.messaging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab1.messaging.service.MessagingService;


@RestController
public class MessagingController {

    @Autowired
    private MessagingService messagingService;

    @PostMapping("/")
    public ResponseEntity<String> addMessageToQueue(@RequestParam("message") String message) {
        messagingService.sendMessageToQueue(message);
        System.out.println(message);
        return ResponseEntity.status(HttpStatus.CREATED).body("Message added to queue successfully");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity.ok(messagingService.getAllMessages());
    }
}
