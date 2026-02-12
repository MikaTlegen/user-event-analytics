package com.example.user_event_analytics.dto.request_dto;

import com.example.user_event_analytics.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Email(message = "Invalid email format")
    @Size(max = 100)
    private String email;

    private Role userRole;
}