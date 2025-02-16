package com.microservices.userservice.service.impl;

import com.microservices.userservice.exception.PasswordNotValidException;
import com.microservices.userservice.exception.UserNotFoundException;
import com.microservices.userservice.model.user.Token;
import com.microservices.userservice.model.user.dto.request.LoginRequest;
import com.microservices.userservice.model.user.entity.UserEntity;
import com.microservices.userservice.repository.UserRepository;
import com.microservices.userservice.service.TokenService;
import com.microservices.userservice.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    @Override
    public Token login(LoginRequest loginRequest) {

        final UserEntity userEntityFromDB = userRepository
                .findUserEntityByEmail(loginRequest.getEmail())
                .orElseThrow(
                        () -> new UserNotFoundException("Can't find with given email: "
                                + loginRequest.getEmail())
                );

        if (Boolean.FALSE.equals(passwordEncoder.matches(
                loginRequest.getPassword(), userEntityFromDB.getPassword()))) {
            throw new PasswordNotValidException();
        }
        return tokenService.generateToken(userEntityFromDB.getClaims());
    }
}
