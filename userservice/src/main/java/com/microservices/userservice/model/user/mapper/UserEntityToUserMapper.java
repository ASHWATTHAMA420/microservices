package com.microservices.userservice.model.user.mapper;

import com.microservices.userservice.model.common.mapper.BaseMapper;
import com.microservices.userservice.model.user.User;
import com.microservices.userservice.model.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityToUserMapper extends BaseMapper<UserEntity, User> {

    @Override
    User map(UserEntity source);

    static UserEntityToUserMapper initialize() {
        return Mappers.getMapper(UserEntityToUserMapper.class);
    }
}
