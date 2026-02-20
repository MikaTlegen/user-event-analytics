package com.example.user_event_analytics.mapper;

import com.example.user_event_analytics.dto.request_dto.UserEventRequestDTO;
import com.example.user_event_analytics.dto.response_dto.UserEventResponseDTO;
import com.example.user_event_analytics.entity.UserEvent;
import org.springframework.stereotype.Component;

@Component
public class UserEventMapper {
    public UserEvent mapToEntity(UserEventRequestDTO userEventRequestDTO) {
        UserEvent userEvent = new UserEvent();
        userEvent.setUserId(userEventRequestDTO.getUserId());
        userEvent.setEventType(userEventRequestDTO.getEventType());
        userEvent.setDetails(userEventRequestDTO.getDetails());
        return userEvent;
    }

    public UserEventResponseDTO mapToResponseDTO(UserEvent userEvent) {
        return UserEventResponseDTO.builder()
                .id(userEvent.getId())
                .userId(userEvent.getUserId())
                .eventType(userEvent.getEventType())
                .details(userEvent.getDetails())
                .build();
    }
}

