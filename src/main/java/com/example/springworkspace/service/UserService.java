package com.example.springworkspace.service;

import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByKey(String key);
    Iterable<UserDTO> getAllUsers();
}
