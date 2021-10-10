package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.response.UserResponse;
import com.example.demo.model.request.user.CreateUserRequest;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.specification.UserSpecification;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final ProfileRepository profileRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepo, ProfileRepository profileRepository, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.profileRepository = profileRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse save(CreateUserRequest request) {
        User user = userMapper.to(request);
        return userMapper.to(userRepo.saveAndFlush(user));
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_USER)
        );
        return userMapper.to(user);
    }

    @Override
    public Page<UserResponse> findALL(Pageable pageable) {
        Page<User> users = userRepo.findAll(
                UserSpecification.filter(null, null),
                pageable
        );
        return users.map(userMapper::to);
    }
}
