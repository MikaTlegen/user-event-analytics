package com.example.user_event_analytics.service;

import com.example.user_event_analytics.dto.request_dto.UserEventRequestDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final String TOPIC = "user_event";
    private final KafkaTemplate<String, UserEventRequestDTO> kafkaTemplate;

    public void sendMassage(UserEventRequestDTO message) {
        System.out.println("Отправка сообщения в Kafka: " + message);
        kafkaTemplate.send(TOPIC, message);
    }


}
