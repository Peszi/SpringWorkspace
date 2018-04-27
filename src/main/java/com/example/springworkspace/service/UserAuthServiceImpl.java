package com.example.springworkspace.service;

import com.example.springworkspace.command.Credentials;
import com.example.springworkspace.data.FullUserDTO;
import com.example.springworkspace.exception.BadRequestException;
import com.example.springworkspace.mapper.UserMapper;
import com.example.springworkspace.model.User;
import com.example.springworkspace.service.util.AuditLoggerService;
import com.example.springworkspace.service.util.AuthService;
import com.example.springworkspace.service.util.MessageService;
import org.springframework.stereotype.Service;


@Service
public class UserAuthServiceImpl implements UserAuthService {

    private AuditLoggerService loggerService;
    private MessageService messageService;

    private AuthService authService;
    private UserMapper userMapper;

    private UserService userService;

    public UserAuthServiceImpl(AuditLoggerService loggerService, MessageService messageService, AuthService authService, UserMapper userMapper, UserService userService) {
        this.loggerService = loggerService;
        this.messageService = messageService;
        this.authService = authService;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    public String registerUser(final String ip, Credentials credentials) {
        this.validateCredentials(credentials);
        this.loggerService.userRegisterAttempt(ip, credentials);
        if (this.userService.createUser(new User(credentials.getUsername(),
                this.authService.encodePassword(credentials.getPassword())).setApiKey(this.authService.getApiKey()))) {
            this.loggerService.userRegistered(ip, credentials);
            return this.messageService.getMessage("user.register.success");
        }
        throw new BadRequestException(this.messageService.getMessage("user.register.fail"));
    }

    @Override
    public String deleteUser(final String ip, String api) {
        this.loggerService.userDeleteAttempt(ip, api);
        if (this.userService.deleteUser(api)) {
            this.loggerService.userDeleted(ip, api);
            return this.messageService.getMessage("user.delete.success");
        }
        throw new BadRequestException(this.messageService.getMessage("user.delete.fail"));
    }

    @Override
    public FullUserDTO authorizeUser(final String ip, Credentials credentials) {
        this.validateCredentials(credentials);
        this.loggerService.userLoginAttempt(ip, credentials);
        return this.authService.validateAndGetUser(this.userService.getUserByName(credentials.getUsername()), credentials.getPassword()).map(
                user -> { this.loggerService.userLogin(ip, credentials);
                    return this.userMapper.userToFullUserDTO(user); }
        ).orElseThrow(() -> new BadRequestException(this.messageService.getMessage("user.login.password.wrong")));
    }

    // Validations

    private void validateCredentials(Credentials credentials) {
        if (!credentials.isInit()) { throw new BadRequestException(this.messageService.getMessage("credentials.incorrect")); };
    }
}
