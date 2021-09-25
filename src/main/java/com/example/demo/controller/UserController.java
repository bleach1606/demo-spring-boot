package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.reponse.response.UserResponse;
import com.example.demo.model.request.user.CreateUserRequest;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public-api/v1.0.0/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public BaseResponse<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        return BaseResponse.ofSuccess(userService.save(request));
    }

    @GetMapping("{id}")
    public BaseResponse<UserResponse> findById(@PathVariable Long id) {
        return BaseResponse.ofSuccess(userService.findById(id));
    }

    @GetMapping("")
    public BaseResponse<?> findAll() {
        return BaseResponse.ofSuccess(userService.findALL());
    }

}
