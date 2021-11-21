package com.example.demo.service.impl;

import com.example.demo.entity.Notification;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.model.Message.NotificationMessage;
import com.example.demo.repository.NotificationRepo;
import com.example.demo.service.NotificationService;
import com.example.demo.service.NotificationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepo notificationRepo;

    private final NotificationUserService notificationUserService;

    private final NotificationMapper notificationMapper;

    @Override
    public Notification save(NotificationMessage message) {
        Notification notification = notificationMapper.to(message);

        // save notification
        notification = notificationRepo.save(notification);

        // save user receive notification
        notificationUserService.saveAll(notification, message.getUsers());
        return notification;
    }

}
