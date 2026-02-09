package com.example.user_event_analytics.service;

import com.example.user_event_analytics.entity.UserEvent;
import com.example.user_event_analytics.repository.UserEventRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserEventService {
    private final UserEventRepository userEventRepository;

    public UserEventService(UserEventRepository userEventRepository) {
        this.userEventRepository = userEventRepository;
    }


    public UserEvent saveEvent(UserEvent userEvent) {
        if (userEvent.getTimeStamp() == null) {
            userEvent.setTimeStamp(new Date());
        }
        return userEventRepository.save(userEvent);
    }

    public List<UserEvent> getAllEvents() {
        return userEventRepository.findAll();
    }

    public List<UserEvent> getEventsByUserId(Long userId) {
        return userEventRepository.findByUserId(userId);
    }

}
