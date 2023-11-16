package com.nesflisback.nesflisback.service;

import com.nesflisback.nesflisback.controller.dto.UserInputDTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface UserService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUsername(String username);
    String createUser(UserInputDTO userDTO);
    void deleteUser(String userId);
    void updateUser(String userId, UserInputDTO userDTO);
}
