package com.example.test_task_agencyamazon2.service;

import com.example.test_task_agencyamazon2.exception.RegistrationException;
import com.example.test_task_agencyamazon2.model.User;

public interface UserService {
    User register(User registrationUser) throws RegistrationException;

    User getByEmail(String email);
}
