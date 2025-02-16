package com.microservice.authservice.service.impl;

import com.microservice.authservice.client.UserServiceClient;
import com.microservice.authservice.model.auth.dto.request.TokenInvalidateRequest;
import com.microservice.authservice.model.common.dto.response.CustomResponse;
import com.microservice.authservice.service.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {

    private final UserServiceClient userServiceClient;

    @Override
    public CustomResponse<Void> logout(TokenInvalidateRequest tokenInvalidateRequest) {
        return userServiceClient.logout(tokenInvalidateRequest);
    }

}
