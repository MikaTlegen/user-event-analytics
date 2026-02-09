package com.example.user_event_analytics.repository;

import com.example.user_event_analytics.entity.UserEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventRepository extends MongoRepository<UserEvent, String> {
    List<UserEvent> findByUserId(Long userId);
}
