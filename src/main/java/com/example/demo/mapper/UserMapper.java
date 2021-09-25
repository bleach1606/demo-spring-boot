package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.model.reponse.response.UserResponse;
import com.example.demo.model.request.user.CreateUserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User> {

    public User to(CreateUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }

    public UserResponse to(User user) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

}
