package com.example.demo.service;

import com.example.demo.entity.Notification;
import com.example.demo.model.Message.UserMessage;
import com.example.demo.model.reponse.NotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface NotificationUserService {

    void saveAll(Notification notificationId , List<UserMessage> users);

    Page<NotificationResponse> findNotificationsByUserId(UUID userId, Pageable pageable);
}
