package com.nesflisback.nesflisback.service;

import com.nesflisback.nesflisback.controller.dto.UserInputDTO;
import com.nesflisback.nesflisback.controller.dto.UserOutputDTO;

import java.util.List;

public interface UserService {
    UserOutputDTO getUserById(String id);
    UserOutputDTO createUser(UserInputDTO userDTO);
    void updateUser(String userId, UserInputDTO userDTO);
    List<UserOutputDTO> findAllUsers(int pageNumber, int pageSize);
}
