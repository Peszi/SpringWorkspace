package com.example.springworkspace.service;

import com.example.springworkspace.command.Credentials;
import com.example.springworkspace.model.User;

import java.util.Optional;

public interface UserAuthorizationService {
    boolean isUserExists(String username);
    boolean isUserExistsByApiKey(String apiKey);
    boolean registerUser(Credentials userCredentials);
    Optional<User> authorizeUser(Credentials userCredentials);
    boolean deregisterUser(String apiKey);
}
