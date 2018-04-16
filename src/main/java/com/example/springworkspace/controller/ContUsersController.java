package com.example.springworkspace.controller;

import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.data.UserListDTO;
import com.example.springworkspace.repository.UserRepository;
import com.example.springworkspace.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ContUsersController {

    private final UserService userService;

    public ContUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserListDTO> getAllUsers() {
        return new ResponseEntity<>(new UserListDTO(this.userService.getAllUsers()), HttpStatus.OK);
    }
}
