package com.example.springworkspace.restcontroller;

import com.example.springworkspace.data.RoomDTO;
import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.exception.BadRequestException;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.model.User;
import com.example.springworkspace.service.MessageService;
import com.example.springworkspace.service.RoomService;
import com.example.springworkspace.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomRestController {

    private MessageService messageService;

    private UserService userService;
    private RoomService roomService;

    public RoomRestController(MessageService messageService, UserService userService, RoomService roomService) {
        this.messageService = messageService;
        this.userService = userService;
        this.roomService = roomService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RoomDTO> createRoom(@RequestParam String key, Locale locale) {
        User user = this.getUserByKey(key, locale);

        Optional<RoomDTO> room = this.roomService.createRoom(user);
        if (room.isPresent()) {
            return new ResponseEntity<>(room.get(), HttpStatus.CREATED);
        }
        throw new BadRequestException(this.messageService.getMessage("room.create.fail", locale));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRoom(@RequestParam String key, Locale locale) {
        User user = this.getUserByKey(key, locale);

        if (this.roomService.removeRoom(user.getId())) {
            return new ResponseEntity<>(this.messageService.getMessage("room.remove.success", locale), HttpStatus.OK);
        }
        throw new BadRequestException(this.messageService.getMessage("room.remove.fail", locale));
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public void joinUserRoom(@RequestParam String key, @RequestParam String roomId, Locale locale) {
        User user = this.getUserByKey(key, locale);

    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public void getUserRoom(@RequestParam String key, Locale locale) {
        User user = this.getUserByKey(key, locale);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public void leaveUserRoom(@RequestParam String key, Locale locale) {
        User user = this.getUserByKey(key, locale);
    }

    private User getUserByKey(String key, Locale locale) {
        final Optional<User> user = this.userService.getUserByKey(key);
        if (!user.isPresent())
            throw new NotFoundException(this.messageService.getMessage("user.data.not.exists", locale));
        return user.get();
    }

}
