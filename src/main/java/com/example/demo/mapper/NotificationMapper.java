package com.example.demo.mapper;

import com.example.demo.entity.Notification;
import com.example.demo.model.Message.NotificationMessage;
import com.example.demo.model.reponse.NotificationResponse;
import com.example.demo.utils.BeanUtilsCus;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper implements Mapper<Notification> {

    public Notification to(NotificationMessage message) {
        Notification notification = new Notification();
        BeanUtilsCus.refine(message, notification, BeanUtilsCus::copyNonNull);
        return notification;
    }

    public NotificationResponse to(Notification entity) {
        NotificationResponse response = new NotificationResponse();
        BeanUtilsCus.refine(entity, response, BeanUtilsCus::copyNonNull);
        response.setTimeSend(entity.getTimeSend().getTime());
        return response;
    }

}
