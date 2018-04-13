package com.example.springworkspace.service;

import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.mapper.UserMapper;
import com.example.springworkspace.model.User;
import com.example.springworkspace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByKey(String key) {
        return this.userRepository.findByApiKey(key);
    }

    @Override
    public Iterable<UserDTO> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(this.userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }
}
