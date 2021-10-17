package com.example.demo.service;

import com.example.demo.model.UserDTO;
import org.keycloak.representations.AccessTokenResponse;

public interface KeycloakService {

    UserDTO createUser(UserDTO userDTO);

    AccessTokenResponse signup(UserDTO userDTO);

    void logoutUser(String userId);

    void resetPassword(String newPassword, String userId);
}
