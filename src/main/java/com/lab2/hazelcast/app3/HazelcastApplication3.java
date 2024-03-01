package com.lab2.hazelcast.app3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

@SpringBootApplication
@PropertySource(value = {"hazel/third.properties", "application.properties" })
public class HazelcastApplication3 {
    public static void main(String[] args) {
        SpringApplication.run(HazelcastApplication3.class, args);
        Config helloWorldConfig = new Config();
        helloWorldConfig.setClusterName("hello-world");
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(helloWorldConfig);

        IMap<String, Integer> map = hz.getMap("my-distributed-map");

        String key = "key";
        map.putIfAbsent(key, 0);

        for (int i = 0; i < 1000; ++i) {
            if ( i % 100 == 0 ) System.out.println( "At: " + i );
            // map.lock( key );
            // try {
            //     Integer value = map.get( key );
            //     Thread.sleep( 10 );
            //     value++;
            //     map.put( key, value );
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // } finally {
            //     map.unlock( key );
            // }
            for (; ; ) {
                Integer oldValue = map.get( key );
                @SuppressWarnings("removal")
                Integer newValue = new Integer( oldValue );
                try {
                    Thread.sleep( 10 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                newValue++;
                if ( map.replace( key, oldValue, newValue ) )
                    break;
            }
        }
        System.out.println("From app #3 : " + map.get(key));
    }
}
