package com.example.user_event_analytics.controller;

import com.example.user_event_analytics.dto.UserEventRequestDTO;
import com.example.user_event_analytics.dto.response_dto.EventTypeStatsDTO;
import com.example.user_event_analytics.dto.response_dto.UserEventResponseDTO;
import com.example.user_event_analytics.service.UserEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Slf4j
public class UserEventController {

    private final UserEventService userEventService;

    @PostMapping
    public ResponseEntity<UserEventResponseDTO> createEvent(@Valid @RequestBody UserEventRequestDTO userEvent) {

        UserEventResponseDTO savedEvent = userEventService.saveEvent(userEvent);
        return ResponseEntity.ok(savedEvent);
    }


    @GetMapping
    public ResponseEntity<List<UserEventResponseDTO>> getAllEvents() {
        return ResponseEntity.ok(userEventService.getAllEvents());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserEventResponseDTO>> getEventsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userEventService.getEventsByUserId(userId));
    }

    @GetMapping("/stats/type")
    public ResponseEntity<List<EventTypeStatsDTO>> getEventsByType() {
        return ResponseEntity.ok(userEventService.getEventsCountByType());
    }
}
