package com.example.springworkspace.service;

import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.exception.ConflictException;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.mapper.UserMapper;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;
import com.example.springworkspace.repository.UserRepository;
import com.example.springworkspace.service.util.MessageService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private MessageService messageService;

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(MessageService messageService, UserMapper userMapper, UserRepository userRepository) {
        this.messageService = messageService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean createUser(User user) {
        this.validateUserAlreadyExists(user.getUsername());
        this.userRepository.save(user);
        if (this.userRepository.findByUsername(user.getUsername()).isPresent())
            return true;
        return false;
    }

    @Override
    public boolean deleteUser(String api) {
        this.userRepository.delete(this.validateAndGetUserWithKey(api));
        if (!this.userRepository.findByApiKey(api).isPresent())
            return true;
        return false;
    }

    @Override
    public User getUserByName(String username) {
        return this.validateAndGetUser(username);
    }

    @Override
    public User getUserByApi(String api) {
        return this.validateAndGetUserWithKey(api);
    }

    @Override
    public Iterable<UserDTO> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(this.userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean joinRoom(String api, Room room) {
        User user = this.validateAndGetUserWithKey(api);
        if (!user.hasRoom()) {
            user.setRoom(room);
            this.userRepository.flush();
            return true;
        }
        return false;
    }

    @Override
    public boolean leaveRoom(String api) {
        User user = this.validateAndGetUserWithKey(api);
        if (user.hasRoom()) {
            user.removeRoom();
            this.userRepository.flush();
            return true;
        }
        return false;
    }

    // Validators

    private User validateAndGetUser(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException(this.messageService.getMessage("user.not.found")));
    }

    private User validateAndGetUserWithKey(String api) {
        return this.userRepository.findByApiKey(api).orElseThrow(
                () -> new NotFoundException(this.messageService.getMessage("user.not.found")));
    }

    private void validateUserAlreadyExists(String username) {
        this.userRepository.findByUsername(username).ifPresent(
                user -> { throw new ConflictException(this.messageService.getMessage("user.already.exists")); });
    }
}
