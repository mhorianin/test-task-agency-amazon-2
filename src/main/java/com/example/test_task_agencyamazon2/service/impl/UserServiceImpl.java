package com.example.test_task_agencyamazon2.service.impl;

import com.example.test_task_agencyamazon2.exception.RegistrationException;
import com.example.test_task_agencyamazon2.model.User;
import com.example.test_task_agencyamazon2.repository.UserRepository;
import com.example.test_task_agencyamazon2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(User registrationUser) throws RegistrationException {
        if (repository.findByEmail(registrationUser.getEmail()).isPresent()) {
            throw new RegistrationException("Can't register user");
        }
        registrationUser.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
        registrationUser.setRole("ROLE_USER");

        return repository.save(registrationUser);
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Can't find user by email:" + email)
        );
    }
}
