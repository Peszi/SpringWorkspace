package com.example.springworkspace.restcontroller;

import com.example.springworkspace.data.FullUserDTO;
import com.example.springworkspace.exception.BadRequestException;
import com.example.springworkspace.exception.ConflictException;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.command.Credentials;
import com.example.springworkspace.service.AuditLoggerService;
import com.example.springworkspace.service.MessageService;
import com.example.springworkspace.service.UserAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserRestController {

    private AuditLoggerService loggerService;
    private MessageService messageService;

    private UserAuthorizationService authorizationService;

    public UserRestController(AuditLoggerService loggerService, MessageService messageService, UserAuthorizationService authorizationService) {
        this.loggerService = loggerService;
        this.messageService = messageService;
        this.authorizationService = authorizationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<FullUserDTO> loginUser(@ModelAttribute Credentials credentials, HttpServletRequest request, Locale locale) { // @RequestParam(value="username") String username, @RequestParam(value="password") String password
        this.loggerService.userLogging(request.getRemoteAddr(), credentials);
        if (!this.authorizationService.isUserExists(credentials.getUsername()))
            throw new NotFoundException(this.messageService.getMessage("user.not.exists", locale));

        Optional<FullUserDTO> apiUserDTO = this.authorizationService.authorizeUser(credentials);
        if (apiUserDTO.isPresent()) {
            this.loggerService.userLogin(request.getRemoteAddr(), credentials);
            return new ResponseEntity<>(apiUserDTO.get(), HttpStatus.OK);
        }
        throw new BadRequestException(this.messageService.getMessage("user.login.password.wrong", locale));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@ModelAttribute Credentials credentials, HttpServletRequest request, Locale locale) {
        this.loggerService.userRegistering(request.getRemoteAddr(), credentials);
        if (this.authorizationService.isUserExists(credentials.getUsername()))
            throw new ConflictException(this.messageService.getMessage("user.already.exists", locale));

        if (this.authorizationService.registerUser(credentials)) {
            this.loggerService.userRegistered(request.getRemoteAddr(), credentials);
            return new ResponseEntity<>(this.messageService.getMessage("user.register.success", locale), HttpStatus.CREATED);
        }
        throw new BadRequestException(this.messageService.getMessage("user.register.fail", locale));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deregisterUser(@RequestParam(value="apiKey") String apiKey, HttpServletRequest request, Locale locale) {
        this.loggerService.userDeleting(request.getRemoteAddr(), apiKey);
        if (!this.authorizationService.isUserExistsByApiKey(apiKey))
            throw new NotFoundException(this.messageService.getMessage("user.not.exists", locale));

        if (this.authorizationService.deregisterUser(apiKey)) {
            this.loggerService.userDeleted(request.getRemoteAddr(), apiKey);
            return new ResponseEntity<>(this.messageService.getMessage("user.remove.success", locale), HttpStatus.OK);
        }
        throw new BadRequestException(this.messageService.getMessage("user.remove.fail", locale));
    }

}
