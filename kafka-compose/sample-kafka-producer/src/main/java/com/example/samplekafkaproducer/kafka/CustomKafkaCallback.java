package com.example.samplekafkaproducer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class CustomKafkaCallback<K, V> implements ListenableFutureCallback<SendResult<K, V>> {

    private final Object message;

    public CustomKafkaCallback(Object message) {
        this.message = message;
    }

    @Override
    public void onSuccess(SendResult<K, V> result) {
        log.info("Sent message = [ {} ] with topic {} with offset=[ {} ]", message.toString(), result.getRecordMetadata().topic(), result.getRecordMetadata().offset());
    }

    @Override
    public void onFailure(Throwable ex) {
        log.info("Unable to send message=[ {} ] due to : {} " , message , ex.getMessage());
    }
}
