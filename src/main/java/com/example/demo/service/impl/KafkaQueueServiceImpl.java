package com.example.demo.service.impl;

import com.example.demo.model.Message.NotificationMessage;
import com.example.demo.service.KafkaQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaQueueServiceImpl implements KafkaQueueService {

    @Value(value = "${kafka.topic-demo}")
    private String demoReputation;

    private final KafkaTemplate<String, NotificationMessage> notificationKafkaTemplate;

    @Override
    public void sendNotificationToKafka(NotificationMessage message) {
        String traceId = "traceId";

        log.info("Send association to Kafka topic: {} - [{}]", demoReputation, message);

        ListenableFuture<SendResult<String, NotificationMessage>> future = notificationKafkaTemplate.send(
                demoReputation,
                traceId,
                message
        );
        future.addCallback(new ListenableFutureCallback<SendResult<String, NotificationMessage>>() {
            @Override
            public void onSuccess(SendResult<String, NotificationMessage> result) {
                log.info("Sent association message=[" + result.getProducerRecord().key() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(@NotNull Throwable throwable) {
                log.error("Unable to send association message=["
                        + traceId + "] due to: " + throwable.getMessage(), throwable);
            }
        });
    }

}
