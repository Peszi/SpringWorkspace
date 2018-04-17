package com.example.springworkspace.service;

import com.example.springworkspace.data.UserListDTO;
import com.example.springworkspace.exception.ConflictException;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.mapper.UserMapper;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;
import com.example.springworkspace.repository.UserRepository;
import com.example.springworkspace.service.util.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public boolean createUser(User user) {
        this.validateUserAlreadyExists(user.getUsername());
        this.userRepository.save(user);
        if (this.userRepository.findByUsername(user.getUsername()).isPresent())
            return true;
        return false;
    }

    @Override
    @Transactional
    public boolean deleteUser(String api) {
        this.userRepository.delete(this.validateAndGetUserWithKey(api));
        if (!this.userRepository.findByApiKey(api).isPresent())
            return true;
        return false;
    }

    @Override
    @Transactional
    public boolean joinRoom(User user, Room room) {
        if (user != null && !user.hasRoom()) {
            user.setRoom(room);
            this.userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean leaveRoom(User user) {
        if (user != null && user.hasRoom()) {
            user.removeRoom();
            this.userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean setReady(User user) {
        if (user != null && user.hasRoom() && !user.getIsReady()) {
            user.setReady(true);
            this.userRepository.save(user);
            return true;
        }
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
    public User getUserById(long userId) {
        return this.validateAndGetUser(userId);
    }

    @Override
    public UserListDTO getAllUsers() {
        return new UserListDTO(this.userRepository.findAll()
                .stream()
                .map(this.userMapper::userToUserDTO)
                .collect(Collectors.toList()));
    }

    // Validators

    private User validateAndGetUser(long userId) {
        return this.userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(this.messageService.getMessage("user.not.found")));
    }

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
