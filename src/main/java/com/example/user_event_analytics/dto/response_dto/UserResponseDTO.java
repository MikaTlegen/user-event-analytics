package com.example.user_event_analytics.dto.response_dto;

import com.example.user_event_analytics.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Role userRole;
    private UserProfileResponseDTO userProfile;
}