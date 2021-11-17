package ru.sberbank.samplemetricstest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.samplemetricstest.entities.User;
import ru.sberbank.samplemetricstest.repository.UserRepo;

@RestController("/users")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<User> addUser(User user) {
        User save = userRepo.save(user);
        return ResponseEntity.status(201).body(save);
    }
}
