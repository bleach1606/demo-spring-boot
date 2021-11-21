package com.example.demo.service;

import com.example.demo.entity.Notification;
import com.example.demo.model.Message.NotificationMessage;

public interface NotificationService {

    Notification save(NotificationMessage message);
}
