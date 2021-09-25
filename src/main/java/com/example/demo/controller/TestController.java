package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.BaseResponse;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("public-api/v1.0.0/test")
public class TestController {

    private UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/1")
    public BaseResponse<?> test1() {
        return BaseResponse.ofSuccess("test");
    }

    @GetMapping("/2")
    public BaseResponse<?> test2() {
        List<String> list = Arrays.asList("1", "2", "3");
        return BaseResponse.ofSuccess(list);
    }

    @PostMapping
    public BaseResponse<?> saveUser() {
        return BaseResponse.ofSuccess(userRepository.save(User.builder().username("admin").password("123456").build()));
    }
}
