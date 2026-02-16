package com.example.user_event_analytics.dto.response_dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeStatsDTO {
    private String eventType;
    private Long count;
}