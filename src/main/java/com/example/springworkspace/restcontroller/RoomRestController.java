package com.example.springworkspace.restcontroller;

import com.example.springworkspace.data.RoomListDTO;
import com.example.springworkspace.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomRestController {

    private RoomService roomService;

    public RoomRestController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RoomListDTO getAllRooms() {
        return new RoomListDTO(this.roomService.getAllRooms());
    }

}
