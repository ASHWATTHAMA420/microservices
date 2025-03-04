package com.microservices.userservice.model.user;

import com.microservices.userservice.model.common.BaseDomainModel;
import com.microservices.userservice.model.user.enums.UserStatus;
import com.microservices.userservice.model.user.enums.UserType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseDomainModel {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserStatus userStatus;
    private UserType userType;
    private String password;
}
