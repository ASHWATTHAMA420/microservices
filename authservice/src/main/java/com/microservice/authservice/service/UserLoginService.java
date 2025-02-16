package com.microservice.authservice.service;

import com.microservice.authservice.model.auth.dto.request.LoginRequest;
import com.microservice.authservice.model.auth.dto.response.TokenResponse;
import com.microservice.authservice.model.common.dto.response.CustomResponse;

public interface UserLoginService {

    CustomResponse<TokenResponse> login(final LoginRequest loginRequest);

}
