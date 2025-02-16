package com.microservices.userservice.service;

import com.microservices.userservice.model.user.Token;
import com.microservices.userservice.model.user.dto.request.TokenRefreshRequest;

public interface RefreshTokenService {

    Token refreshToken(final TokenRefreshRequest tokenRefreshRequest);
}
