package com.example.demo.service.impl;

import com.example.demo.entity.Notification;
import com.example.demo.entity.NotificationUser;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.mapper.NotificationUserMapper;
import com.example.demo.model.Message.UserMessage;
import com.example.demo.model.reponse.NotificationResponse;
import com.example.demo.repository.NotificationUserRepo;
import com.example.demo.service.NotificationUserReadService;
import com.example.demo.service.NotificationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationUserServiceImpl implements NotificationUserService {

    private final NotificationUserReadService notificationUserReadService;

    private final NotificationUserRepo notificationUserRepo;

    private final NotificationUserMapper notificationUserMapper;
    private final NotificationMapper notificationMapper;
    @Override
    public void saveAll(Notification notificationId, List<UserMessage> users) {

        List<NotificationUser> notificationUsers =
                users.stream().map( userMessage -> {
                    NotificationUser notificationUser = notificationUserMapper.to(userMessage);
                    notificationUser.setNotification(notificationId);
                    return notificationUser;
                }).collect(Collectors.toList());

        notificationUserRepo.saveAll(notificationUsers);
    }

    @Override
    public Page<NotificationResponse> findNotificationsByUserId(UUID userId, Pageable pageable) {

        Page<NotificationUser> notificationUsers = notificationUserRepo
                .findNotificationsByUserId(userId, pageable);

        // ds UUID noti trả về
        List<UUID> notificationIds = new ArrayList<>();
        notificationUsers.forEach(
                notificationUser -> notificationIds.add(notificationUser.getNotification().getId())
        );

        // Lấy ds notification đã đọc
        Map<UUID, Boolean> map = notificationUserReadService.checkReadNotifications(userId, notificationIds);

        return notificationUsers.map(
                notificationUser -> {
                    // map về response
                    NotificationResponse response = notificationMapper.to(notificationUser.getNotification());
                    // map đã đọc hay chưa
                    response.setIsRead(map.get(notificationUser.getNotification().getId()));
                    return response;
                }
        );
    }

}
