package com.mlisena.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Document(collection = "users_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    @Id
    private String id;
    @Indexed(unique = true)
    private UUID userId;
    @TextIndexed
    private String firstName;
    @TextIndexed
    private String lastName;
    private Map<String, Object> extraData;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
