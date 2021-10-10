package com.example.demo.mapper;

import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import com.example.demo.model.request.user.CreateUserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.example.demo.model.response.UserResponse;
import com.example.demo.model.response.ProfileResponse;

@Component
public class UserMapper implements Mapper<User> {

    public User to(CreateUserRequest request) {
        User user = new User();
        Profile profile = new Profile();
        BeanUtils.copyProperties(request, user);
        BeanUtils.copyProperties(request.getProfile(), profile);
        user.setProfile(profile);
        return user;
    }

    public UserResponse to(User user) {
        UserResponse response = new UserResponse();
        ProfileResponse profile = new ProfileResponse();
        BeanUtils.copyProperties(user, response);
        BeanUtils.copyProperties(user.getProfile(), profile);
        response.setProfile(profile);
        return response;
    }

}
