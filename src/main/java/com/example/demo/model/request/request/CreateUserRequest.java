package com.example.demo.model.request.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String username;

    private String password;

    private ProfileRequest profile;
}
