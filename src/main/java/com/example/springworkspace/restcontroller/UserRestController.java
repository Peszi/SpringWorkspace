package com.example.springworkspace.restcontroller;

import com.example.springworkspace.data.FullUserDTO;
import com.example.springworkspace.command.Credentials;
import com.example.springworkspace.service.util.AuditLoggerService;
import com.example.springworkspace.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserRestController {

    private UserAuthService authorizationService;

    public UserRestController(UserAuthService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestParam @NotEmpty String username, @RequestParam @NotEmpty String password, HttpServletRequest request) {
        final Credentials credentials = new Credentials(username, password);
        return this.authorizationService.registerUser(request.getRemoteAddr(), credentials);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@RequestParam(value="api") String api, HttpServletRequest request) {
        return this.authorizationService.deleteUser(request.getRemoteAddr(), api);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FullUserDTO loginUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request) { // @Valid Credentials credentials
        final Credentials credentials = new Credentials(username, password);
        return this.authorizationService.authorizeUser(request.getRemoteAddr(), credentials);
    }
}
