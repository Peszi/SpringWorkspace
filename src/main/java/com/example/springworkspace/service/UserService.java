package com.example.springworkspace.service;

import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;

import java.util.Optional;

public interface UserService {
    boolean createUser(User user);
    boolean deleteUser(String api);
    Iterable<UserDTO> getAllUsers();
    User getUserByName(String username);
    User getUserByApi(String api);
    boolean joinRoom(String api, Room room);
    boolean leaveRoom(String api);
}
