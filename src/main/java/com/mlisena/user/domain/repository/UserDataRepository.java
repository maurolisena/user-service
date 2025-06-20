package com.mlisena.user.domain.repository;

import com.mlisena.user.domain.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDataRepository extends MongoRepository<UserData, String> {
    Optional<UserData> findByUserId(UUID userId);
}
