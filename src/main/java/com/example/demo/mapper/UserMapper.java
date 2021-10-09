package com.example.demo.mapper;

import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import com.example.demo.model.reponse.response.ProfileResponse;
import com.example.demo.model.reponse.response.UserResponse;
import com.example.demo.model.request.request.CreateUserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

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
