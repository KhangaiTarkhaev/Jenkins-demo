package ru.sberbank.samplemetricstest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.samplemetricstest.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
