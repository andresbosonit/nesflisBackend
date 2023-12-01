package com.nesflisback.nesflisback.service.impl;

import com.nesflisback.nesflisback.controller.dto.UserInputDTO;
import com.nesflisback.nesflisback.controller.dto.UserOutputDTO;
import com.nesflisback.nesflisback.exceptions.EntityNotFoundException;
import com.nesflisback.nesflisback.repository.SubscriptionRepository;
import com.nesflisback.nesflisback.repository.UserRepository;
import com.nesflisback.nesflisback.service.UserService;
import com.nesflisback.nesflisback.domain.User;
import com.nesflisback.nesflisback.util.KeycloakProvider;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Override
    public UserOutputDTO getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario con ID: " + id));
        return user.userToUserOutputDto();
    }

    @Override
    public UserOutputDTO createUser(UserInputDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(userDTO.getIdUser());
        if(userOptional.isPresent()) new EntityNotFoundException("Ya exite un usuario con id: " + userDTO.getIdUser());
        return userRepository.save(new User(userDTO)).userToUserOutputDto();
    }

    @Override
    public void updateUser(String userId, UserInputDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario con ID: " + userId));
        if(userDTO.getUsername() != null){
            user.setUsername(userDTO.getUsername());
        }
        if(userDTO.getEmail() != null){
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getFirstName() != null){
            user.setFirstName(userDTO.getFirstName());
        }
        if(userDTO.getLastName() != null){
            user.setLastName(userDTO.getLastName());
        }
        if(userDTO.getPassword() != null){
            user.setPassword(userDTO.getPassword());
        }
        if(userDTO.getEmailVerified() != null){
            user.setEmailVerified(userDTO.getEmailVerified());
        }
        if(userDTO.getUnsubscribeDate() != null){
            user.setUnsubscribeDate(userDTO.getUnsubscribeDate());
        }
        userRepository.save(user).userToUserOutputDto();
    }

    @Override
    public List<UserOutputDTO> findAllUsers(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageRequest).getContent()
                .stream()
                .map(User::userToUserOutputDto).toList();
    }








}
