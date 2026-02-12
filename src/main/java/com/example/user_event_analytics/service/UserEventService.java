package com.example.user_event_analytics.service;

import com.example.user_event_analytics.dto.UserEventRequestDTO;
import com.example.user_event_analytics.dto.response_dto.UserEventResponseDTO;
import com.example.user_event_analytics.entity.UserEvent;
import com.example.user_event_analytics.mapper.UserEventMapper;
import com.example.user_event_analytics.repository.UserEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserEventService {
    private final UserEventRepository userEventRepository;
    private final UserEventMapper userEventMapper;

    public UserEventResponseDTO saveEvent(UserEventRequestDTO requestDTO) {
        UserEvent userEvent = userEventMapper.mapToEntity(requestDTO);
        if (userEvent.getTimeStamp() == null) {
            userEvent.setTimeStamp(new Date());
        }

        UserEvent savedEvent = userEventRepository.save(userEvent);
        return userEventMapper.mapToResponseDTO(savedEvent);
    }

    public List<UserEventResponseDTO> getAllEvents() {
        return userEventRepository.findAll().stream()
                .map(userEventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<UserEventResponseDTO> getEventsByUserId(Long userId) {
        return userEventRepository.findByUserId(userId).stream()
                .map(userEventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

}
