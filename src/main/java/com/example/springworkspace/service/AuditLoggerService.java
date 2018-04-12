package com.example.springworkspace.service;

import com.example.springworkspace.command.Credentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditLoggerService {

    public void userLogging(String remoteAddr, Credentials credentials) {
        log.info("User logging... ip[" + remoteAddr + "] credentials[" + credentials.getUsername() + ":" + credentials.getPassword() + "]");
    }

    public void userLogin(Credentials credentials) {
        log.warn("User " + "[" + credentials.getUsername() + "] successfully log in!");
    }

    public void userRegistering(String remoteAddr, Credentials credentials) {
        log.info("User registering... ip[" + remoteAddr + "] credentials[" + credentials.getUsername() + ":" + credentials.getPassword() + "]");
    }

    public void userRegistered(Credentials credentials) {
        log.warn("User " + "[" + credentials.getUsername() + "] successfully registered in!");
    }

    public void userDeleting(String remoteAddr, String apiKey) {
        log.info("User deleting... ip[" + remoteAddr + "] key[" + apiKey + "]");
    }

    public void userDeleted(String key) {
        log.warn("User key[" + key + "]" + " deleted!");
    }
}
