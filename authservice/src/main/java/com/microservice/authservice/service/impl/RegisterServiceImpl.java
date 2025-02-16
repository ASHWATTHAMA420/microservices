package com.microservice.authservice.service.impl;

import com.microservice.authservice.client.UserServiceClient;
import com.microservice.authservice.model.auth.User;
import com.microservice.authservice.model.auth.dto.request.RegisterRequest;
import com.microservice.authservice.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserServiceClient userServiceClient;

    @Override
    public User registerUser(RegisterRequest registerRequest) {
        return userServiceClient.register(registerRequest).getBody();
    }

}
