package com.example.springworkspace.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

@Configuration
public class BasicConfiguration {

    public static final int API_KEY_LENGTH = 32;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BytesKeyGenerator getBytesKeyGenerator() {
        return KeyGenerators.secureRandom(BasicConfiguration.API_KEY_LENGTH/2);
    }

}
