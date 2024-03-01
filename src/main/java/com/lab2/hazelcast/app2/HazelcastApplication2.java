package com.lab2.hazelcast.app2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@PropertySource(value = {"hazel/second.properties", "application.properties" })
public class HazelcastApplication2 {
    @SuppressWarnings("removal")
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(HazelcastApplication2.class, args);
        Config helloWorldConfig = new Config();
        helloWorldConfig.setClusterName("hello-world");
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(helloWorldConfig);

        IQueue<Integer> queue = hz.getQueue( "myQueue" ); 

        for ( int k = 1; k < 100; k++ ) {
            queue.put( new Integer(k) );
            System.out.println( "Producing: " + k );
            Thread.sleep(1000);
        }
        queue.put( -1 );
        System.out.println( "Producer Finished!" );
    }
}
