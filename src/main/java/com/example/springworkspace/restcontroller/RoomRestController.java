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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createRoom(@RequestParam String api, HttpServletRequest request) {
        return this.roomService.createRoom(request.getRemoteAddr(), api);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteRoom(@RequestParam String api, HttpServletRequest request) {
        return this.roomService.deleteRoom(request.getRemoteAddr(), api);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RoomListDTO getAllRooms() {
        return new RoomListDTO(this.roomService.getAllRooms());
    }

    // Get by room id


    // Get all

    //    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<RoomDTO> createRoom(@RequestParam String key, Locale locale) {
//        User user = this.getUserByApi(key, locale);
//
//        Optional<RoomDTO> room = this.roomService.createRoom(user);
//        if (room.isPresent()) {
//            return new ResponseEntity<>(room.get(), HttpStatus.CREATED);
//        }
//        throw new BadRequestException(this.messageService.getMessage("room.create.fail", locale));
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE)
//    public ResponseEntity<String> deleteRoom(@RequestParam String key, Locale locale) {
//        User user = this.getUserByApi(key, locale);
//
//        if (this.roomService.deleteRoom(user.getId())) {
//            return new ResponseEntity<>(this.messageService.getMessage("room.remove.success", locale), HttpStatus.OK);
//        }
//        throw new BadRequestException(this.messageService.getMessage("room.remove.fail", locale));
//    }
//
//    @RequestMapping(value = "/user", method = RequestMethod.PUT)
//    public void joinUserRoom(@RequestParam String key, @RequestParam long roomId, Locale locale) {
//        User user = this.getUserByApi(key, locale);
//        this.userService.addRoom(user, this.roomService.getRawRoomById(roomId).get());
//    }
//
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public void getUserRoom(@RequestParam String key, Locale locale) {
//        User user = this.getUserByApi(key, locale);
//    }
//
//    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
//    public void leaveUserRoom(@RequestParam String key, Locale locale) {
//        User user = this.getUserByApi(key, locale);
//    }
//
//    private User getUserByApi(String key, Locale locale) {
//        final Optional<User> user = this.userService.getUserByApi(key);
//        if (!user.isPresent())
//            throw new NotFoundException(this.messageService.getMessage("user.data.not.exists", locale));
//        return user.get();
//    }

    // Validators

}
