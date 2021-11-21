package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.Message.NotificationMessage;
import com.example.demo.model.Message.UserMessage;
import com.example.demo.service.KafkaQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("public-api/v1.0.0/notification")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final KafkaQueueService kafkaQueueService;

    @GetMapping()
    public BaseResponse<Object> getUserById() {
        NotificationMessage message = fakeNotificationMessage();
        kafkaQueueService.sendNotificationToKafka(message);
        return BaseResponse.ofSuccess(message);
    }

    public NotificationMessage fakeNotificationMessage() {
        return NotificationMessage
                .builder()
                .contentEn("contentEn")
                .contentVi("contentVi")
                .note("note")
                .descriptionEn("desEn")
                .descriptionVi("desVi")
                .targetId(UUID.randomUUID())
                .timeSend(new Timestamp(System.currentTimeMillis()))
                .titleEn("titleEn")
                .titleVi("titleVi")
//                .users(fakesUserMessage())
                .build();
    }

    public List<UserMessage> fakesUserMessage() {
        List<UserMessage> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserMessage userMessage = UserMessage
                    .builder()
                    .userId(UUID.randomUUID())
                    .fcmToken("fcmToken")
                    .build();
            users.add(userMessage);
        }
        return users;
    }

}
