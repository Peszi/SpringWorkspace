package com.example.springworkspace.restcontroller;

import com.example.springworkspace.data.FullRoomDTO;
import com.example.springworkspace.data.RoomDTO;
import com.example.springworkspace.data.RoomListDTO;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.service.UserRoomService;
import com.example.springworkspace.service.util.MessageService;
import com.example.springworkspace.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/room")
public class UserRoomRestController {

    private UserRoomService userRoomService;

    public UserRoomRestController(UserRoomService userRoomService) {
        this.userRoomService = userRoomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String joinRoom(@RequestParam String api, @RequestParam long roomId) {
        return this.userRoomService.joinRoom(api, roomId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String leaveRoom(@RequestParam String api) {
        return this.userRoomService.leaveRoom(api);
    }

    @PostMapping("/ready")
    @ResponseStatus(HttpStatus.OK)
    public String setReady(@RequestParam String api) {
        return this.userRoomService.setReady(api);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FullRoomDTO getAllRooms(@RequestParam String api) {
        return this.userRoomService.getRoom(api);
    }
}
