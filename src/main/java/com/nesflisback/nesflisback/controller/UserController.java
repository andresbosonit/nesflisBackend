package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.controller.dto.UserInputDTO;
import com.nesflisback.nesflisback.controller.dto.UserOutputDTO;
import com.nesflisback.nesflisback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<?> findAllUsers(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                          @RequestParam(defaultValue = "4", required = false) int pageSize){
        return ResponseEntity.ok(userService.findAllUsers(pageNumber, pageSize));
    }

    @GetMapping("/search/{idUser}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable String idUser){
         return ResponseEntity.ok(userService.getUserById(idUser));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserInputDTO userDTO) throws URISyntaxException {
        UserOutputDTO response = userService.createUser(userDTO);
        return ResponseEntity.created(new URI("/user/create")).body(response);
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserInputDTO userDTO){
        userService.updateUser(userId, userDTO);
        return ResponseEntity.ok("User updated successfully");
    }

}
