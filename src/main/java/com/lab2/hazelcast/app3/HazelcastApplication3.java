package com.lab2.hazelcast.app3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@PropertySource(value = {"hazel/third.properties", "application.properties" })
public class HazelcastApplication3 {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(HazelcastApplication3.class, args);
        Config helloWorldConfig = new Config();
        helloWorldConfig.setClusterName("hello-world");

        QueueConfig qc = helloWorldConfig.getQueueConfig("myQueue");
        qc.setMaxSize(10);
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(helloWorldConfig);

        IQueue<Integer> queue = hz.getQueue( "myQueue" );
        while ( true ) {
            int item = queue.take(); 
            System.out.println( "Consumed: " + item );
            if ( item == -1 ) {
                queue.put( -1 );
                break;
            }
            Thread.sleep( 5000 ); 
        }
    }
}
