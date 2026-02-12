package com.example.user_event_analytics.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEventResponseDTO {

    private String id;
    private Long userId;
    private String eventType;
    private Date timeStamp;
    private String details;
}