package com.example.springworkspace.service.util;

import com.example.springworkspace.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthService {

    private BCryptPasswordEncoder passwordEncoder;
    private BytesKeyGenerator keyGenerator;

    public AuthService(BCryptPasswordEncoder passwordEncoder, BytesKeyGenerator keyGenerator) {
        this.passwordEncoder = passwordEncoder;
        this.keyGenerator = keyGenerator;
    }

    public String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    public Optional<User> validateAndGetUser(User user, String password) {
        if (password != null && this.passwordEncoder.matches(password, user.getPassword()))
            return Optional.of(user);
        return Optional.empty();
    }

    public String getApiKey() {
        return new String(Hex.encode(this.keyGenerator.generateKey()));
    }
}
