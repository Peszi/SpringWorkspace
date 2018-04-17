package com.example.springworkspace.restcontroller;

import com.example.springworkspace.data.FullUserDTO;
import com.example.springworkspace.command.Credentials;
import com.example.springworkspace.exception.BadRequestException;
import com.example.springworkspace.service.util.AuditLoggerService;
import com.example.springworkspace.service.UserAuthService;
import com.example.springworkspace.service.util.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserRestController {

    private MessageService messageService;
    private UserAuthService authorizationService;

    public UserRestController(MessageService messageService, UserAuthService authorizationService) {
        this.messageService = messageService;
        this.authorizationService = authorizationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@Valid Credentials credentials, BindingResult bindingResult, HttpServletRequest request) {
        if (!bindingResult.hasErrors())
            this.authorizationService.registerUser(this.getIpAddress(request), credentials);
        throw this.handleIncorrectCredentials();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@RequestParam(value="api") String api, HttpServletRequest request) {
        return this.authorizationService.deleteUser(this.getIpAddress(request), api);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FullUserDTO loginUser(@Valid Credentials credentials, BindingResult bindingResult, HttpServletRequest request) { // @RequestParam String username, @RequestParam String password
        if (!bindingResult.hasErrors())
            return this.authorizationService.authorizeUser(this.getIpAddress(request), credentials);
        throw this.handleIncorrectCredentials();
    }

    private RuntimeException handleIncorrectCredentials() {
        return new BadRequestException(this.messageService.getMessage("credentials.incorrect"));
    }

    private String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null)
            return request.getRemoteAddr();
        return "unknown";
    }
}
