package com.example.springworkspace.service;

import com.example.springworkspace.command.Credentials;
import com.example.springworkspace.data.FullUserDTO;

import java.util.Optional;

public interface UserAuthService {
    String registerUser(final String ip, Credentials userCredentials);
    FullUserDTO authorizeUser(final String ip, Credentials userCredentials);
    String deleteUser(final String ip, String apiKey);
}
