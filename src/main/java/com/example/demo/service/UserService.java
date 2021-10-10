package com.example.demo.service;

import com.example.demo.model.response.UserResponse;
import com.example.demo.model.request.user.CreateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    UserResponse save(CreateUserRequest request);

    UserResponse findById(Long id);

    Page<UserResponse> findALL(Pageable pageable);

}
