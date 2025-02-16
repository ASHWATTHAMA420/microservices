package com.microservices.userservice.service.impl;

import com.microservices.userservice.exception.UserAlreadyExistException;
import com.microservices.userservice.model.user.User;
import com.microservices.userservice.model.user.dto.request.RegisterRequest;
import com.microservices.userservice.model.user.entity.UserEntity;
import com.microservices.userservice.model.user.mapper.RegisterRequestToUserEntityMapper;
import com.microservices.userservice.model.user.mapper.UserEntityToUserMapper;
import com.microservices.userservice.repository.UserRepository;
import com.microservices.userservice.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;

    private final RegisterRequestToUserEntityMapper registerRequestToUserEntityMapper = RegisterRequestToUserEntityMapper.initialize();

    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();

    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterRequest registerRequest) {

        if (userRepository.existsUserEntityByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistException("The email is already used for another admin : " + registerRequest.getEmail());
        }

        final UserEntity userEntityToBeSave = registerRequestToUserEntityMapper.mapForSaving(registerRequest);

        userEntityToBeSave.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        UserEntity savedUserEntity = userRepository.save(userEntityToBeSave);

        return userEntityToUserMapper.map(savedUserEntity);

    }

}
