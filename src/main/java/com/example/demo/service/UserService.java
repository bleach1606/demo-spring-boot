package com.example.demo.service;

import com.example.demo.model.reponse.response.UserResponse;
import com.example.demo.model.request.user.CreateUserRequest;

import java.util.List;

public interface UserService {

    UserResponse save(CreateUserRequest request);

    UserResponse findById(Long id);

    List<UserResponse> findALL();
}
