package com.microservices.userservice.model.user.mapper;

import com.microservices.userservice.model.common.mapper.BaseMapper;
import com.microservices.userservice.model.user.dto.request.RegisterRequest;
import com.microservices.userservice.model.user.entity.UserEntity;
import com.microservices.userservice.model.user.enums.UserType;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterRequestToUserEntityMapper extends BaseMapper<RegisterRequest, UserEntity> {

    @Named("mapForSaving")
    default UserEntity mapForSaving(RegisterRequest userRegisterRequest) {

        UserType userType = "admin".equalsIgnoreCase(userRegisterRequest.getRole()) ? UserType.ADMIN : UserType.USER;

        return UserEntity.builder()
                .email(userRegisterRequest.getEmail())
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .phoneNumber(userRegisterRequest.getPhoneNumber())
                .userType(userType)
                .build();
    }

    static RegisterRequestToUserEntityMapper initialize() {
        return Mappers.getMapper(RegisterRequestToUserEntityMapper.class);
    }
}