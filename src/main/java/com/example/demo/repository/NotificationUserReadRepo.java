package com.example.demo.repository;

import com.example.demo.entity.NotificationUserRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface NotificationUserReadRepo extends JpaRepository<NotificationUserRead, UUID> {

    @Query(value = " select nur.notification.id " +
            " from NotificationUserRead nur" +
            " where nur.notification.id in :notifications" +
            " and nur.userId = :userId ")
    List<UUID> findNotificationUserReadBYUserId(UUID userId, List<UUID> notifications);
}
