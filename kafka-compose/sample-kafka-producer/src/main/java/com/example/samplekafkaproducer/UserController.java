package com.example.samplekafkaproducer;

import com.example.samplekafkaproducer.kafka.CustomKafkaCallback;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final UserRepo userRepo;

    public UserController(KafkaTemplate<String, String> kafkaTemplate, UserRepo userRepo) {
        this.kafkaTemplate = kafkaTemplate;
        this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        kafkaTemplate.send("user-created", user.toString()).addCallback(new CustomKafkaCallback<>(user.toString()));
        User save = userRepo.save(user);
        return ResponseEntity.status(201).body(save);
    }
}
