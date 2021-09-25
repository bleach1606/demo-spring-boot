package com.example.demo.model.request.user;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String username;

    private String password;
}
