package com.example.user_event_analytics.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEventEntityResponseDTO {

    private Long id;
    private String userId;
    private String eventType;
    private LocalDateTime timestamp;
}
