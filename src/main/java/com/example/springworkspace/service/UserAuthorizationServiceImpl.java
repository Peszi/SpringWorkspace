package com.example.springworkspace.service;

import com.example.springworkspace.command.Credentials;
import com.example.springworkspace.model.User;
import com.example.springworkspace.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthorizationServiceImpl implements UserAuthorizationService {

    private BCryptPasswordEncoder passwordEncoder;
    private BytesKeyGenerator keyGenerator;

    private UserRepository userRepository;

    public UserAuthorizationServiceImpl(BCryptPasswordEncoder passwordEncoder, BytesKeyGenerator keyGenerator, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.keyGenerator = keyGenerator;
        this.userRepository = userRepository;
    }

    @Override
    public boolean isUserExists(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean isUserExistsByApiKey(String apiKey) {
        return this.userRepository.findByApiKey(apiKey).isPresent();
    }

    @Override
    public boolean registerUser(Credentials userCredentials) {
        if (!this.isUserExists(userCredentials.getUsername())) {
            this.userRepository.save(new User(userCredentials.getUsername(), this.passwordEncoder.encode(userCredentials.getPassword())).setApiKey(this.getApiKey()));
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> authorizeUser(Credentials userCredentials) {
        return this.checkUserPassword(this.userRepository.findByUsername(userCredentials.getUsername()), userCredentials.getPassword());
    }

    @Override
    public boolean deregisterUser(String apiKey) {
        if (this.removeUser(this.userRepository.findByApiKey(apiKey)))
            return !this.isUserExistsByApiKey(apiKey);
        return false;
    }

    private Optional<User> checkUserPassword(Optional<User> user, String password) {
        if (user.isPresent() && password != null)
            return this.passwordEncoder.matches(password, user.get().getPassword()) ? Optional.of(user.get()) : Optional.empty();
        return Optional.empty();
    }

    private boolean removeUser(Optional<User> user) {
        if (user.isPresent()) {
            this.userRepository.delete(user.get()); return true;
        }
        return false;
    }

    private String getApiKey() {
        return new String(Hex.encode(this.keyGenerator.generateKey()));
    }
}
