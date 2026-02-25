package com.example.user_event_analytics.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleStatsDTO {
    private String role;
    private Long userCount;
    private Double percentage;
}