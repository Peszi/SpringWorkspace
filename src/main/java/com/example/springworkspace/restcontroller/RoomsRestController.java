package com.example.springworkspace.restcontroller;

import com.example.springworkspace.data.RoomDTO;
import com.example.springworkspace.data.RoomListDTO;
import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.data.UserListDTO;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.service.MessageService;
import com.example.springworkspace.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomsRestController {

    private MessageService messageService;
    private RoomService roomService;

    public RoomsRestController(MessageService messageService, RoomService roomService) {
        this.messageService = messageService;
        this.roomService = roomService;
    }

    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    public ResponseEntity<RoomDTO> getRoom(@PathVariable("roomId") long roomId) {
        Optional<RoomDTO> room = this.roomService.getRoomById(roomId);
        if (room.isPresent())
            return new ResponseEntity<>(room.get(), HttpStatus.OK);
        throw new NotFoundException("Room not found!");
    }

    @RequestMapping(value = "/raw/{roomId}", method = RequestMethod.GET)
    public ResponseEntity<Room> getRawRoom(@PathVariable("roomId") long roomId) {
        Optional<Room> room = this.roomService.getRawRoomById(roomId);
        if (room.isPresent())
            return new ResponseEntity<>(room.get(), HttpStatus.OK);
        throw new NotFoundException("Room not found!");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<RoomListDTO> getAllRooms() {
        return new ResponseEntity<>(new RoomListDTO(this.roomService.getAllRooms()), HttpStatus.OK);
    }
}
