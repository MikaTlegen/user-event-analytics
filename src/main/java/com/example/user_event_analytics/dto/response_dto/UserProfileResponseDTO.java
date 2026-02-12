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
public class UserProfileResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Long userId;
    private LocalDateTime createdAt;
}