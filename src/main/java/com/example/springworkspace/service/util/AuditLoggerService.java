package com.example.springworkspace.service.util;

import com.example.springworkspace.command.Credentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditLoggerService {

    // User manage

    public void userRegisterAttempt(String ip, Credentials credentials) {
        log.info(this.getPrefix(ip) + " signing with credentials ... [" + credentials.getUsername() + ":" + credentials.getPassword() + "]");
    }

    public void userRegistered(String ip, Credentials credentials) {
        log.warn(this.getPrefix(ip) + " signed in! [" + credentials.getUsername() + "]");
    }

    public void userDeleteAttempt(String ip, String key) {
        log.info(this.getPrefix(ip) + " deleting account... key[" + key + "]");
    }

    public void userDeleted(String ip, String key) {
        log.warn(this.getPrefix(ip) + " account deleted! key[" + key + "]");
    }

    public void userLoginAttempt(String ip, Credentials credentials) {
        log.info(this.getPrefix(ip) + " logging with credentials... [" + credentials.getUsername() + ":" + credentials.getPassword() + "]");
    }

    public void userLogin(String ip, Credentials credentials) {
        log.warn(this.getPrefix(ip) + " logged in! [" + credentials.getUsername() + "]");
    }

    // Room manage

    public void roomCreated(String ip, long roomId) {
        log.info(this.getPrefix(ip) + " room created! id[" + roomId + "]");
    }

    public void roomDeleted(String ip, long roomId) {
        log.info(this.getPrefix(ip) + " room deleted! id[" + roomId + "]");
    }

    private String getPrefix(String remoteAddr) {
        return "[" + remoteAddr + "]";
    }
}
