package com.example.user_event_analytics.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "user_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {
    @Id
    private String id;
    @Field(name = "user_id")
    private Long userId;
    @Field(name = "event_type")
    private String eventType;
    @NotNull
    @Field(name = "time_stamp")
    @CreationTimestamp
    private Date timeStamp;
    @Field(name = "details")
    @Size(max = 100)
    private String details;

}
