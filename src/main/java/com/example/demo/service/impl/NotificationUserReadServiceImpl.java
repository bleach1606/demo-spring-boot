package com.example.demo.service.impl;


import com.example.demo.entity.Notification;
import com.example.demo.entity.NotificationUserRead;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.NotificationRepo;
import com.example.demo.repository.NotificationUserReadRepo;
import com.example.demo.service.NotificationUserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationUserReadServiceImpl implements NotificationUserReadService {

    private final NotificationUserReadRepo notificationUserReadRepo;
    private final NotificationRepo notificationRepo;

    @Override
    public void readNotification(UUID userId, UUID notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new BusinessException(BusinessCode.NOT_FOUND_NOTIFICATION));
        NotificationUserRead userRead = new NotificationUserRead();
        userRead.setUserId(userId);
        userRead.setNotification(notification);
        notificationUserReadRepo.save(userRead);
    }

    @Override
    public Map<UUID, Boolean> checkReadNotifications(UUID userId, List<UUID> notificationIds) {
        Map<UUID, Boolean> map = new HashMap<>();

        // đánh giấu tất cả noti đều chưa đọc
        notificationIds.forEach(uuid -> map.put(uuid, Boolean.FALSE));

        // lấy ds noti đã đọc
        List<UUID> notificationReads = notificationUserReadRepo
                .findNotificationUserReadBYUserId(userId, notificationIds);

        // đánh dấu đã đọc là true
        notificationReads.forEach(uuid -> map.put(uuid, Boolean.TRUE));

        return map;
    }
}
