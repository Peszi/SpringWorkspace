package com.example.springworkspace.restcontroller;

import com.example.springworkspace.service.RoomManageService;
import com.example.springworkspace.service.UserRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/room")
public class RoomManageController {

    private RoomManageService roomManageService;

    public RoomManageController(RoomManageService roomManageService) {
        this.roomManageService = roomManageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createRoom(@RequestParam String api, HttpServletRequest request) {
        return this.roomManageService.createRoom(api);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteRoom(@RequestParam String api, HttpServletRequest request) {
        return this.roomManageService.deleteRoom(api);
    }

    @PostMapping("/kick")
    @ResponseStatus(HttpStatus.OK)
    public String kickUser(@RequestParam String api, @RequestParam long userId) {
        return this.roomManageService.kickUser(api, userId);
    }

    @PostMapping("/start")
    @ResponseStatus(HttpStatus.OK)
    public String startGame(@RequestParam String api) {
        return this.roomManageService.startGame(api);
    }
}
