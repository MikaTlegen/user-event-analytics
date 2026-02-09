package com.example.user_event_analytics.controller;

import com.example.user_event_analytics.entity.UserEvent;
import com.example.user_event_analytics.service.UserEventService;
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
    public ResponseEntity<UserEvent> createEvent(@RequestBody UserEvent userEvent) {
        log.info(">>> Получено событие: userId={}, eventType={}, details={}",
                userEvent.getUserId(), userEvent.getEventType(), userEvent.getDetails());

        UserEvent savedEvent = userEventService.saveEvent(userEvent);
        log.info(">>> Сохранено событие: id={}, userId={}",
                savedEvent.getId(), savedEvent.getUserId());

        return ResponseEntity.ok(savedEvent);
    }


    @GetMapping
    public ResponseEntity<List<UserEvent>> getAllEvents() {
        return ResponseEntity.ok(userEventService.getAllEvents());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserEvent>> getEventsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userEventService.getEventsByUserId(userId));
    }
}
