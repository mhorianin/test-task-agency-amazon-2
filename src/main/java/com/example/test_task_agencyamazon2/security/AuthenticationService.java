package com.example.test_task_agencyamazon2.security;

import com.example.test_task_agencyamazon2.dto.UserLoginResponseDto;
import com.example.test_task_agencyamazon2.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserLoginResponseDto authenticate(User requestDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(), requestDto.getPassword()
                )
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponseDto(token);
    }
}
