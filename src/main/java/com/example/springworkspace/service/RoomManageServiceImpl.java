package com.example.springworkspace.service;

import com.example.springworkspace.exception.BadRequestException;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;
import com.example.springworkspace.service.util.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomManageServiceImpl implements RoomManageService {

    private MessageService messageService;

    private UserService userService;
    private RoomService roomService;

    public RoomManageServiceImpl(MessageService messageService, UserService userService, RoomService roomService) {
        this.messageService = messageService;
        this.userService = userService;
        this.roomService = roomService;
    }

    @Override
    public String createRoom(String api) {
        if (this.roomService.createRoom(this.userService.getUserByApi(api)))
            return this.messageService.getMessage("room.create.success");
        return this.messageService.getMessage("room.create.fail");
    }

    @Override
    public String deleteRoom(String api) {
        if (this.roomService.deleteRoom(this.userService.getUserByApi(api)))
            return this.messageService.getMessage("room.delete.success");
        return this.messageService.getMessage("room.delete.fail");
    }

    @Override
    @Transactional
    public String kickUser(String api, long userId) {
        User user = this.userService.getUserByApi(api);
        Room room = this.roomService.getRoomByHostId(user.getId());
        if (room.getUsers().stream().noneMatch(u -> u.getId() == userId))
            throw new NotFoundException(this.messageService.getMessage("room.not.exists"));
        if (this.userService.leaveRoom(user))
            return this.messageService.getMessage("room.kick.success");
        throw new BadRequestException(this.messageService.getMessage("room.kick.fail"));
    }

    @Override
    @Transactional
    public String startGame(String api) {
        if (this.roomService.startGame(this.userService.getUserByApi(api)))
            return "Game started!";
        return "Cannot start!";
    }
}
