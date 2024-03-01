package com.lab1.logging.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class LoggingService {
    
    private Map<Long, String> logMap = new HashMap<>();

    public String getAllLogs() {
        String mapAsString = logMap.keySet().stream()
        .map(key -> logMap.get(key))
        .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }

    public String addLog(Long uuid, String log) {
        logMap.put(uuid, log);
        return log;
    }

}
