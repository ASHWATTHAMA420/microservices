package com.microservices.userservice.model.user.mapper;

import com.microservices.userservice.model.common.mapper.BaseMapper;
import com.microservices.userservice.model.user.Token;
import com.microservices.userservice.model.user.dto.response.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TokenToTokenResponseMapper extends BaseMapper<Token, TokenResponse> {

    @Override
    TokenResponse map(Token source);

    static TokenToTokenResponseMapper initialize() {
        return Mappers.getMapper(TokenToTokenResponseMapper.class);
    }
}
