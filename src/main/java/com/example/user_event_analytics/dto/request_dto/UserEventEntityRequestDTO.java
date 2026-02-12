package com.example.user_event_analytics.dto.request_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEventEntityRequestDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String eventType;
}