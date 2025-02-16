package com.microservices.userservice.service;

import com.microservices.userservice.model.user.dto.request.TokenInvalidateRequest;

public interface LogoutService {

    void logout(final TokenInvalidateRequest tokenInvalidateRequest);
}
