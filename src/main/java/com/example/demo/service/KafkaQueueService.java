package com.example.demo.service;


import com.example.demo.model.Message.NotificationMessage;

public interface KafkaQueueService {

    void sendNotificationToKafka(NotificationMessage message);
}
