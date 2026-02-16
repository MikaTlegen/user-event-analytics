package com.example.user_event_analytics.repository;

import com.example.user_event_analytics.entity.UserEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventPostgresEntity extends JpaRepository<UserEventEntity, Long> {

    @Query(value = "SELECT event_type, COUNT(id) FROM user_events_entity GROUP BY event_type ORDER BY COUNT(id) DESC",
            nativeQuery = true)
    List<Object[]> countEventsByTypeNative();

}
