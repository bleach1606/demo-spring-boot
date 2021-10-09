package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.reponse.response.UserResponse;
import com.example.demo.model.request.request.CreateUserRequest;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue = "15"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string",
                    paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                    + "Default sort order is ascending. Multiple sort criteria are supported.")})
    public BaseResponse<?> findAll(@ApiIgnore Pageable pageable) {
        return BaseResponse.ofSuccess(userService.findALL(pageable));
    }

}
