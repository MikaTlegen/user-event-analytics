package com.example.user_event_analytics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_events_entity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "event_type", nullable = false)
    private String eventType;
    @Column(name = "event_data", nullable = false)
    @CreationTimestamp
    private LocalDateTime timestamp;
    @Column(name = "details")
    @Size(max = 100)
    private String details;
}
