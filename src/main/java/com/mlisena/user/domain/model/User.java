package com.mlisena.user.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private UUID id = UUID.randomUUID();

    @TextIndexed
    private String firstName;

    @TextIndexed
    private String lastName;

    @Indexed(unique = true)
    private String email;

    private String password;

    @Indexed
    private boolean active;

    @TextIndexed
    @CreatedDate
    private LocalDateTime createdAt;

    @TextIndexed
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
