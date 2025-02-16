package com.microservice.authservice.service;

import com.microservice.authservice.model.auth.User;
import com.microservice.authservice.model.auth.dto.request.RegisterRequest;

public interface RegisterService {

    User registerUser(final RegisterRequest registerRequest);

}
