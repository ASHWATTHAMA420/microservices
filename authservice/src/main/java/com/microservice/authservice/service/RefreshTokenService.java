package com.microservice.authservice.service;

import com.microservice.authservice.model.auth.dto.request.TokenRefreshRequest;
import com.microservice.authservice.model.auth.dto.response.TokenResponse;
import com.microservice.authservice.model.common.dto.response.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface RefreshTokenService {

    CustomResponse<TokenResponse> refreshToken(@RequestBody @Valid final TokenRefreshRequest tokenRefreshRequest);

}
