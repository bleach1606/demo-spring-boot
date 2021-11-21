package com.example.demo.repository;

import com.example.demo.entity.NotificationUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface NotificationUserRepo extends JpaRepository<NotificationUser, UUID> {

    @Query(value = "select nu " +
            " from NotificationUser nu " +
            " where nu.userId = :userId " +
            " order by nu.createdAt desc ")
    Page<NotificationUser> findNotificationsByUserId(UUID userId, Pageable pageable);
}
