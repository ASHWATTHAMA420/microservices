package com.microservices.userservice.service.impl;

import com.microservices.userservice.exception.UserNotFoundException;
import com.microservices.userservice.exception.UserStatusNotValidException;
import com.microservices.userservice.model.user.Token;
import com.microservices.userservice.model.user.dto.request.TokenRefreshRequest;
import com.microservices.userservice.model.user.entity.UserEntity;
import com.microservices.userservice.model.user.enums.TokenClaims;
import com.microservices.userservice.model.user.enums.UserStatus;
import com.microservices.userservice.repository.UserRepository;
import com.microservices.userservice.service.RefreshTokenService;
import com.microservices.userservice.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    @Override
    public Token refreshToken(TokenRefreshRequest tokenRefreshRequest) {

        tokenService.verifyAndValidate(tokenRefreshRequest.getRefreshToken());

        final String adminId = tokenService
                .getPayload(tokenRefreshRequest.getRefreshToken())
                .get(TokenClaims.USER_ID.getValue())
                .toString();

        final UserEntity userEntityFromDB = userRepository
                .findById(adminId)
                .orElseThrow(UserNotFoundException::new);

        this.validateUserStatus(userEntityFromDB);

        return tokenService.generateToken(
                userEntityFromDB.getClaims(),
                tokenRefreshRequest.getRefreshToken()
        );

    }

    private void validateUserStatus(final UserEntity userEntity) {
        if (!(UserStatus.ACTIVE.equals(userEntity.getUserStatus()))) {
            throw new UserStatusNotValidException("UserStatus = " + userEntity.getUserStatus());
        }
    }
}
