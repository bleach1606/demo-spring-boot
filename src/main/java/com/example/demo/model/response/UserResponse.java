package com.example.demo.model.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String password;
    private ProfileResponse profile;
}
