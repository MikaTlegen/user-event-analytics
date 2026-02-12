package com.example.user_event_analytics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEventRequestDTO {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 50)
    private String eventType;

    @Size(max = 100)
    private String details;
}