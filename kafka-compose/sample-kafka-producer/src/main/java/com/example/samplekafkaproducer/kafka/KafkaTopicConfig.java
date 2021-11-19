package com.example.samplekafkaproducer.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic datesSpringTopic() {
        return TopicBuilder.name("dates-spring")
                .partitions(3)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic randomDigitsTopic() {
        return TopicBuilder.name("random-digits")
                .partitions(3)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic userCreatedTopic() {
        return TopicBuilder.name("user-created")
                .replicas(2)
                .partitions(2)
                .build();
    }
}