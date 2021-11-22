package com.example.samplekafkaproducer.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = {"dates-spring", "random-digits"})
    public void listenGroupFoo(String message) {
        System.out.println("Received Message: " + message);
    }

}
