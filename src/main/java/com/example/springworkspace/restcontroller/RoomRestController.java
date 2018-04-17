package com.example.springworkspace.restcontroller;

import com.example.springworkspace.data.RoomListDTO;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.model.User;
import com.example.springworkspace.service.RoomService;
import com.example.springworkspace.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
