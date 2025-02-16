package com.microservices.userservice.service;

import com.microservices.userservice.model.user.User;
import com.microservices.userservice.model.user.dto.request.RegisterRequest;

public interface RegisterService {

    User registerUser(final RegisterRequest registerRequest);

}
