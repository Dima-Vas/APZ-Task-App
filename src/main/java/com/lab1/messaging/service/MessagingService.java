package com.lab1.messaging.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.collection.IQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagingService {

    private IQueue<String> hzQueue;
    
    public MessagingService(@Value("${myown.clustername}") String aClusterName, HazelcastInstance aHzInstance) {
        hzQueue = aHzInstance.getQueue("queue");
    }

    public void sendMessageToQueue(String message) {
        hzQueue.add(message);
    }

    public List<String> getAllMessages() {
        List<String> messages = new ArrayList<>();
        while (!hzQueue.isEmpty()) {
            messages.add(hzQueue.poll());
        }
        return messages;
    }
}
