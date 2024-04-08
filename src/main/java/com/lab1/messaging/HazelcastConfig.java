package com.lab1.messaging;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config cfg = new Config();
        cfg.setClusterName("messagecluster");
        return Hazelcast.newHazelcastInstance(cfg);
    }
}
