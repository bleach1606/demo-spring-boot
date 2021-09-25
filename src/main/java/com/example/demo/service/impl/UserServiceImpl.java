package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.reponse.response.UserResponse;
import com.example.demo.model.request.user.CreateUserRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse save(CreateUserRequest request) {
        User user = userMapper.to(request);
        return userMapper.to(userRepo.save(user));
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_USER)
        );
        return userMapper.to(user);
    }

    @Override
    public List<UserResponse> findALL() {
        List<User> users = userRepo.findAll();
        return users.stream().map(userMapper::to).collect(Collectors.toList());
    }
}
