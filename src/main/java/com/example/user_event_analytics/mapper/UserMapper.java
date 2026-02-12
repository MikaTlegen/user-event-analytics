package com.example.user_event_analytics.mapper;

import com.example.user_event_analytics.dto.request_dto.UserRequestDTO;
import com.example.user_event_analytics.dto.response_dto.UserResponseDTO;
import com.example.user_event_analytics.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUserRole(dto.getUserRole());
        return user;
    }

    public UserResponseDTO toResponseDto(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .userRole(user.getUserRole())
                .build();
    }
}
