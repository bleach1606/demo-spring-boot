package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface NotificationUserReadService {

    void readNotification(UUID userId, UUID notificationId);

    Map<UUID, Boolean> checkReadNotifications(UUID userId, List<UUID> notificationIds);
}
