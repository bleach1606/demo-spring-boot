package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.Message.NotificationMessage;
import com.example.demo.model.Message.UserMessage;
import com.example.demo.service.KafkaQueueService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.NotificationUserReadService;
import com.example.demo.service.NotificationUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("public-api/v1.0.0/notification")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final KafkaQueueService kafkaQueueService;
    private final NotificationService notificationService;
    private final NotificationUserService notificationUserService;
    private final NotificationUserReadService notificationUserReadService;

    @GetMapping("{userId}/notifications")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue = "15"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string",
                    paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                    + "Default sort order is ascending. Multiple sort criteria are supported.")})
    public BaseResponse<Object> getNotifications(
            @PathVariable("userId") UUID userId,
            @ApiIgnore Pageable pageable
    ) {
        return BaseResponse.ofSuccess(
                notificationUserService.findNotificationsByUserId(userId, pageable)
        );
    }

    @PostMapping("{userId}/read")
    public BaseResponse<Object> readNotification(
            @PathVariable UUID userId,
            @RequestParam UUID notificationId
    ) {
        notificationUserReadService.readNotification(userId, notificationId);
        return BaseResponse.ofSuccess("success");
    }

    @GetMapping("test")
    public BaseResponse<Object> test() {
        NotificationMessage message = fakeNotificationMessage();
        kafkaQueueService.sendNotificationToKafka(message);
        return BaseResponse.ofSuccess(message);
    }

    public NotificationMessage fakeNotificationMessage() {
        return NotificationMessage
                .builder()
                .contentEn("contentEn")
                .contentVi("contentVi")
                .note("note")
                .descriptionEn("desEn")
                .descriptionVi("desVi")
                .targetId(UUID.randomUUID())
                .timeSend(new Timestamp(System.currentTimeMillis()))
                .titleEn("titleEn")
                .titleVi("titleVi")
                .users(fakesUserMessage())
                .build();
    }

    public List<UserMessage> fakesUserMessage() {
        List<UserMessage> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserMessage userMessage = UserMessage
                    .builder()
                    .userId(UUID.randomUUID())
                    .fcmToken("fcmToken")
                    .build();
            users.add(userMessage);
        }
        return users;
    }

}
