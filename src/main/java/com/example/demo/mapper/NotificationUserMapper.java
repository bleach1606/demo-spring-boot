package com.example.demo.mapper;

import com.example.demo.entity.NotificationUser;
import com.example.demo.model.Message.UserMessage;
import com.example.demo.utils.BeanUtilsCus;
import org.springframework.stereotype.Component;

@Component
public class NotificationUserMapper implements Mapper<NotificationUser> {

    public NotificationUser to(UserMessage message) {
        NotificationUser user = new NotificationUser();
        BeanUtilsCus.refine(message, user, BeanUtilsCus::copyNonNull);
        return user;
    }

}
