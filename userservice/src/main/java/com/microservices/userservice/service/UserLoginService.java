package com.microservices.userservice.service;

import com.microservices.userservice.model.user.Token;
import com.microservices.userservice.model.user.dto.request.LoginRequest;

public interface UserLoginService {

    Token login(final LoginRequest loginRequest);
}
