package com.example.user_event_analytics.service;

import com.example.user_event_analytics.dto.request_dto.UserEventRequestDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    UserEventService userEventService;

    @KafkaListener(topics = "user_event", groupId = "group-id")
    public void listen(UserEventRequestDTO message) {
        try {
            System.out.println("Получено сообщение: " + message);
            processMessage(message);
        } catch (Exception e) {
            System.out.println("Ошибка при обработке сообщения: " + e.getMessage());
        }
    }

    private void processMessage(UserEventRequestDTO message) {
        userEventService.saveEvent(message);
    }

}
