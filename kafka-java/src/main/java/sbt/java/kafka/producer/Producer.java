package sbt.java.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

@Component
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String msg) {
        LOGGER.info("sending message='{}' to topic='{}'", msg, topic);
        System.out.println(String.format("Sending... topic - %s, text - %s", topic, msg ));
        kafkaTemplate.send(topic, msg);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void sendMessages() throws InterruptedException {
        while (true) {
            send("topicName", LocalDateTime.now().toString());
            Thread.sleep(5000);
        }
    }
}
