package com.microservice.authservice.model.auth;

import com.microservice.authservice.model.auth.enums.UserStatus;
import com.microservice.authservice.model.auth.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class User {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserStatus userStatus;
    private UserType userType;
}
