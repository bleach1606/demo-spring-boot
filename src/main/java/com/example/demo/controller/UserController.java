package com.example.demo.controller;

import com.example.demo.model.UserDTO;
import com.example.demo.service.KeycloakService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final KeycloakService keycloakService;

    public UserController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }


    @PostMapping(path = "/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(keycloakService.createUser(userDTO));
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<?> signin(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(keycloakService.signup(userDTO));
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {

        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        AccessToken token = ((KeycloakPrincipal<?>) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();

        String userId = token.getSubject();

        keycloakService.logoutUser(userId);

        return new ResponseEntity<>("Hi!, you have logged out successfully!", HttpStatus.OK);

    }

    @GetMapping(value = "/update/password")
    public ResponseEntity<?> updatePassword(HttpServletRequest request, String newPassword) {

        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        AccessToken token = ((KeycloakPrincipal<?>) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();

        String userId = token.getSubject();

        keycloakService.resetPassword(newPassword, userId);

        return new ResponseEntity<>("Hi!, your password has been successfully updated!", HttpStatus.OK);

    }


}
