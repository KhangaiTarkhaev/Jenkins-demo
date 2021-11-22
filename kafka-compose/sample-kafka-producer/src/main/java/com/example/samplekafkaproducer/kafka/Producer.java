package com.example.samplekafkaproducer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String topic, String message) {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, message);
        send.addCallback(new CustomKafkaCallback<>(message));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void produce() throws InterruptedException {
        while (true) {
            publish("dates-spring", LocalDateTime.now().toString());
            publish("random-digits", String.valueOf(ThreadLocalRandom.current().nextInt()));
            try {
                sleep();
            } catch (InterruptedException e) {
                log.info(e.getMessage());
                throw e;
            }
        }
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(3000);
    }

}
