package com.example.springworkspace.service;

import com.example.springworkspace.command.Credentials;
import com.example.springworkspace.data.FullUserDTO;

import java.util.Optional;

public interface UserAuthorizationService {
    boolean isUserExists(String username);
    boolean isUserExistsByApiKey(String apiKey);
    boolean registerUser(Credentials userCredentials);
    Optional<FullUserDTO> authorizeUser(Credentials userCredentials);
    boolean deregisterUser(String apiKey);
}
