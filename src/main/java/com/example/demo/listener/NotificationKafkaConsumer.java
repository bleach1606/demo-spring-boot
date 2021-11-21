package com.example.demo.listener;

import com.example.demo.model.Message.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class NotificationKafkaConsumer {

    @KafkaListener(
            topics = "${kafka.topic-demo}",
            groupId = "${kafka.group-id-demo}",
            containerFactory = "notificationKafkaListenerContainerFactory"
    )
    public void receiveMessage(NotificationMessage message) {
        try {
            log.info("Kafka Received message Content: [{}]", message);
//            this.processor.process(userEventDTO);
        } catch (Exception e) {
            log.error("Kafka Error when sending association: " + message + ", ex: " + e, e);
        }
    }

}
