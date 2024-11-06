package com.example.test_task_agencyamazon2.repository;

import com.example.test_task_agencyamazon2.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
