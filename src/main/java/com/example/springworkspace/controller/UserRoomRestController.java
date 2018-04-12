package com.example.springworkspace.controller;

import com.example.springworkspace.data.UserData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class UserRoomRestController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<UserData> createRoom() {

        return null;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<UserData> removeRoom() {

        return null;
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ResponseEntity<UserData> joinRoom() {

        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserData> getRoom() {

        return null;
    }

    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    public ResponseEntity<UserData> leaveRoom() {

        return null;
    }
}
