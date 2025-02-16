package com.microservice.authservice.service.impl;

import com.microservice.authservice.client.UserServiceClient;
import com.microservice.authservice.model.auth.dto.request.TokenRefreshRequest;
import com.microservice.authservice.model.auth.dto.response.TokenResponse;
import com.microservice.authservice.model.common.dto.response.CustomResponse;
import com.microservice.authservice.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserServiceClient userServiceClient;

    @Override
    public CustomResponse<TokenResponse> refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        return userServiceClient.refreshToken(tokenRefreshRequest);
    }
}
