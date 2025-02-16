package com.microservice.authservice.service;

import com.microservice.authservice.model.auth.dto.request.TokenInvalidateRequest;
import com.microservice.authservice.model.common.dto.response.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface LogoutService {

    CustomResponse<Void> logout(@RequestBody @Valid final TokenInvalidateRequest tokenInvalidateRequest);

}
