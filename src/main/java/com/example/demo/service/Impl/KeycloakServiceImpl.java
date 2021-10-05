package com.example.demo.service.Impl;

import com.example.demo.model.UserDTO;
import com.example.demo.service.KeycloakService;
import lombok.extern.log4j.Log4j2;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class KeycloakServiceImpl implements KeycloakService {

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    private final String role = "app-user";

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(realm)
                .clientId(clientId)
                .username("admin")
                .password("123456")
                .clientSecret(clientSecret)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();

        keycloak.tokenManager().getAccessToken();


        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();

        Response response = usersRessource.create(user);

        userDTO.setStatusCode(response.getStatus());
        userDTO.setStatus(response.getStatusInfo().toString());

        if (response.getStatus() == 201) {

            String userId = CreatedResponseUtil.getCreatedId(response);

            log.info("Created userId {}", userId);


            // create password credential
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(userDTO.getPassword());

            UserResource userResource = usersRessource.get(userId);

            // Set password credential
            userResource.resetPassword(passwordCred);

            // set role
            RoleRepresentation realmRoleUser = realmResource.roles().get(role).toRepresentation();
            userResource.roles().realmLevel().add(Collections.singletonList(realmRoleUser));
        }
        return userDTO;
    }

    @Override
    public AccessTokenResponse signup(UserDTO userDTO) {
        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", clientSecret);
        clientCredentials.put("grant_type", "password");

        Configuration configuration =
                new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);

        return authzClient.obtainAccessToken(userDTO.getEmail(), userDTO.getPassword());
    }

    public void logoutUser(String userId) {

        UsersResource userRessource = getKeycloakUserResource();

        userRessource.get(userId).logout();

    }

    public void resetPassword(String newPassword, String userId) {

        UsersResource userResource = getKeycloakUserResource();

        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(newPassword.toString().trim());

        // Set password credential
        userResource.get(userId).resetPassword(passwordCred);

    }

    private UsersResource getKeycloakUserResource() {

        Keycloak kc = KeycloakBuilder.builder().serverUrl(authServerUrl).realm(realm).username("admin").password("123456")
                .clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();

        RealmResource realmResource = kc.realm(realm);
        UsersResource userRessource = realmResource.users();

        return userRessource;
    }
}
