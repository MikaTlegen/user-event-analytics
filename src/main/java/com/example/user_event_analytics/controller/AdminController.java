package com.example.user_event_analytics.controller;

import com.example.user_event_analytics.dto.response_dto.UserResponseDTO;
import com.example.user_event_analytics.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/users/admins")
    public ResponseEntity<List<UserResponseDTO>> getAllAdminUsers() {
        return ResponseEntity.ok(userService.getAllAdminUsers());
    }
}