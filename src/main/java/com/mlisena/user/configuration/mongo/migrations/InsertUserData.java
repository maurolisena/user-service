package com.mlisena.user.configuration.mongo.migrations;

import com.mlisena.user.domain.model.UserData;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ChangeUnit(id="insert-user-data-changelog-01", order = "001", author = "mongock")
@Slf4j
@RequiredArgsConstructor
public class InsertUserData {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void changeSet() {

        log.info("🚀 Insert initial user data...");

        if (!mongoTemplate.collectionExists("users")) {
            mongoTemplate.createCollection("users");
        }

        if (mongoTemplate.exists(Query.query(Criteria.where("userId").is("8e437e26-4e62-4b37-9a50-fb17c3e4040c")), UserData.class)) {
            log.info("User data already exists, skipping insertion.");
            return;
        }

        Map<String, Object> extraData = Map.of(
            "phone", "589-1234-5678",
            "address", "Buenos Aires, Argentina"
        );

        mongoTemplate.insertAll(List.of(
            UserData.builder()
                .userId(UUID.fromString("8e437e26-4e62-4b37-9a50-fb17c3e4040c"))
                .firstName("Mauro")
                .lastName("Lisena")
                .extraData(extraData)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build()
        ));

        log.info("✅ Successfully insertion of user data.");
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(Query.query(Criteria.where("userId").is("8e437e26-4e62-4b37-9a50-fb17c3e4040c")), UserData.class);
    }
}
