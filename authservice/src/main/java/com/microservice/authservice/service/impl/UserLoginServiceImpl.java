package com.microservice.authservice.service.impl;

import com.microservice.authservice.client.UserServiceClient;
import com.microservice.authservice.model.auth.dto.request.LoginRequest;
import com.microservice.authservice.model.auth.dto.response.TokenResponse;
import com.microservice.authservice.model.common.dto.response.CustomResponse;
import com.microservice.authservice.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final UserServiceClient userServiceClient;

    @Override
    public CustomResponse<TokenResponse> login(LoginRequest loginRequest) {
        return userServiceClient.loginUser(loginRequest);
    }

}
