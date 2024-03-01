package com.lab1.logging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab1.logging.service.LoggingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class LoggingController {
    
    @Autowired
    LoggingService loggingService;

    @GetMapping("/")
    public String getAllLogs() {
        return loggingService.getAllLogs();
    }

    @PostMapping("/")
    public String postNewLog(
        @RequestParam("id") Long id,
        @RequestParam("log") String log
    ) {
        return loggingService.addLog(id, log);
    }

}
