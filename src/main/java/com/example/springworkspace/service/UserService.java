package com.example.springworkspace.service;

import com.example.springworkspace.data.UserListDTO;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;

public interface UserService {
    boolean createUser(User user);
    boolean deleteUser(String api);
    boolean joinRoom(User user, Room room);
    boolean leaveRoom(User user);
    boolean setReady(User user);
    User getUserByName(String username);
    User getUserByApi(String api);
    User getUserById(long userId);
    UserListDTO getAllUsers();
}
