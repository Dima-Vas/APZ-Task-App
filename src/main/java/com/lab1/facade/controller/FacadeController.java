package com.lab1.facade.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



/*
    Functions of Facade :
        1) Accepting the POST request
        2) Generates the unique UUID for it
        3) Sends request to Logging service
        4) Receives the GET request
        5) Resends it to Logging and Messaging services
        6) Receives info from Logging and Messaging services, concatenates it and sends to client
*/


@RestController
public class FacadeController {

    private static Long currUUID = 0l;

    private List<String> loggingPorts = Arrays.asList("8083", "8084", "8088");

    private String messagingPort = "8082";

    private String getResponse(String port) {
        WebClient webClient = WebClient.create();
        String url = String.format("http://localhost:%s/", port);
        @SuppressWarnings("null")
        String responseBody = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return responseBody;
    }

    @GetMapping("/")
    public String getContent() {
        List<String> responses = new ArrayList<>();
        responses.add(getResponse(messagingPort));
        for (String port : loggingPorts) {
            try {
                responses.add(getResponse(port));
            }  catch (Exception e) {
                System.out.println("Warning : it seems that port " + port + " is off.");
            }
        }
        return String.valueOf(responses);
    }

    @PostMapping("/")
    public String postNewLog(
        @RequestParam("log") String log
    ) {
        Long newID = currUUID++;
        WebClient webClient = WebClient.create();
        String url = String.format("http://localhost:%s/", 
                loggingPorts.get(
                    new Random().nextInt(loggingPorts.size())
                    ));
        System.out.println(url);
        Mono<String> requestBody = Mono.just(String.format("id=%d&log=%s", newID, log));
        @SuppressWarnings("null")
        String responseBody = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(requestBody, String.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return responseBody;
    }

}
