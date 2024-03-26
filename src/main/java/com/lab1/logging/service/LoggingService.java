package com.lab1.logging.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Service
public class LoggingService {
    private Map<Long, String> hzMap;
    private HazelcastInstance hzInstance;

    private final String clusterName;

    public LoggingService(@Value("${myown.clustername}") String aClusterName) {
        clusterName = aClusterName;
        Config cfg = new Config();
        System.out.println(clusterName);
        cfg.setClusterName(clusterName);
        hzInstance = Hazelcast.newHazelcastInstance(cfg);
        hzMap = hzInstance.getMap("map");
    }

    public String getAllLogs() {
        String mapAsString = hzMap.
        keySet().
        stream().
        map(key -> hzMap.get(key)).
        collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }

    public String addLog(Long uuid, String log) {
        hzMap.put(uuid, log);
        return log;
    }

}
