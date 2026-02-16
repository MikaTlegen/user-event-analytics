package com.example.user_event_analytics.mapper;

import com.example.user_event_analytics.dto.response_dto.EventTypeStatsDTO;
import org.springframework.stereotype.Component;

@Component
public class EventStatsMapper {
    public EventTypeStatsDTO map(Object[] row) {
        return new EventTypeStatsDTO(
                (String) row[0],
                ((Number) row[1]).longValue()
        );
    }
}
