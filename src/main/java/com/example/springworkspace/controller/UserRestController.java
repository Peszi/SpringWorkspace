package com.example.springworkspace.controller;

import com.example.springworkspace.data.Data;
import com.example.springworkspace.data.UserData;
import com.example.springworkspace.model.Credentials;
import com.example.springworkspace.model.User;
import com.example.springworkspace.service.UserAuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private UserAuthorizationService authorizationService;

    public UserRestController(UserAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Data> loginUser(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        if (this.authorizationService.isUserExists(username)) {
            Optional<User> user = this.authorizationService.authorizeUser(new Credentials(username, password));
            if (user.isPresent())
                return new ResponseEntity<>(new UserData(user.get().getId(), user.get().getApiKey()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new UserData("User not found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserData("Wroong password!"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        if (!this.authorizationService.isUserExists(username)) {
            if (this.authorizationService.registerUser(new Credentials(username, password)))
                return new ResponseEntity<>("User successfully registered!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User already exists!", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Cannot register this user!", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> deregisterUser(@RequestParam(value="apiKey") String apiKey) {
        if (this.authorizationService.isUserExistsByApiKey(apiKey)) {
            if (this.authorizationService.deregisterUser(apiKey))
                return new ResponseEntity<>("User removed!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Cannot remove this user!", HttpStatus.BAD_REQUEST);
    }
}
