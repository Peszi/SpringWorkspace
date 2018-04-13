package com.example.springworkspace.service;

import com.example.springworkspace.command.Credentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditLoggerService {

    public void userLogging(String remoteAddr, Credentials credentials) {
        log.info(this.getPrefix(remoteAddr) + " logging with credentials... [" + credentials.getUsername() + ":" + credentials.getPassword() + "]");
    }

    public void userLogin(String remoteAddr, Credentials credentials) {
        log.warn(this.getPrefix(remoteAddr) + " logged in! [" + credentials.getUsername() + "]");
    }

    public void userRegistering(String remoteAddr, Credentials credentials) {
        log.info(this.getPrefix(remoteAddr) + " signing with credentials ... [" + credentials.getUsername() + ":" + credentials.getPassword() + "]");
    }

    public void userRegistered(String remoteAddr, Credentials credentials) {
        log.warn(this.getPrefix(remoteAddr) + " signed in! [" + credentials.getUsername() + "]");
    }

    public void userDeleting(String remoteAddr, String key) {
        log.info(this.getPrefix(remoteAddr) + " deleting account... key[" + key + "]");
    }

    public void userDeleted(String remoteAddr, String key) {
        log.warn(this.getPrefix(remoteAddr) + " account deleted! key[" + key + "]");
    }

    private String getPrefix(String remoteAddr) {
        return "[" + remoteAddr + "]";
    }
}
