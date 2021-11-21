package com.example.demo.listener;

import com.example.demo.entity.Notification;
import com.example.demo.model.Message.NotificationMessage;
import com.example.demo.service.NotificationService;
import com.example.demo.service.NotificationUserReadService;
import com.example.demo.service.NotificationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Log4j2
@Component
@RequiredArgsConstructor
public class NotificationProcessor {

    private final NotificationService notificationService;

    public void process(NotificationMessage message) {

        if (Objects.nonNull(message)) {
            processNotification(message);
        }
    }

    public void processNotification(NotificationMessage message) {
        log.info("processNotification : {}", message);
        Notification notification = notificationService.save(message);
        log.info("processNotification done : {}", notification);
    }
}
