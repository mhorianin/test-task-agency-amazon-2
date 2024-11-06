package com.example.test_task_agencyamazon2.controller;

import com.example.test_task_agencyamazon2.dto.UserLoginResponseDto;
import com.example.test_task_agencyamazon2.exception.RegistrationException;
import com.example.test_task_agencyamazon2.model.User;
import com.example.test_task_agencyamazon2.security.AuthenticationService;
import com.example.test_task_agencyamazon2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public User register(
            @RequestBody @Valid User registrationRequestDto
    ) throws RegistrationException {
        return userService.register(registrationRequestDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody User request) {
        return authenticationService.authenticate(request);
    }
}
