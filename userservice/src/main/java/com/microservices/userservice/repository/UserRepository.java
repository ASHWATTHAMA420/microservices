package com.microservices.userservice.repository;

import com.microservices.userservice.model.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsUserEntityByEmail(final String email);

    Optional<UserEntity> findUserEntityByEmail(final String email);

}
