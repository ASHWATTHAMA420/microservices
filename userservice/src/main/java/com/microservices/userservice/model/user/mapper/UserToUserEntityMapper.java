package com.microservices.userservice.model.user.mapper;

import com.microservices.userservice.model.common.mapper.BaseMapper;
import com.microservices.userservice.model.user.User;
import com.microservices.userservice.model.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToUserEntityMapper extends BaseMapper<User, UserEntity> {

    @Override
    UserEntity map(User source);

    static UserToUserEntityMapper initialize() {
        return Mappers.getMapper(UserToUserEntityMapper.class);
    }
}
