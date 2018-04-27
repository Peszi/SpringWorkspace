package com.example.springworkspace.service;

import com.example.springworkspace.data.FullRoomDTO;
import com.example.springworkspace.exception.BadRequestException;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.mapper.RoomMapper;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.service.util.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoomServiceImpl implements UserRoomService {

    private MessageService messageService;

    private RoomMapper roomMapper;
    private UserService userService;
    private RoomService roomService;

    public UserRoomServiceImpl(MessageService messageService, RoomMapper roomMapper, UserService userService, RoomService roomService) {
        this.messageService = messageService;
        this.roomMapper = roomMapper;
        this.userService = userService;
        this.roomService = roomService;
    }

    @Override
    @Transactional
    public String joinRoom(String api, long roomId) {
        if (this.userService.joinRoom(this.userService.getUserByApi(api), this.roomService.getRoomById(roomId)))
            return this.messageService.getMessage("room.join.success");
        throw new BadRequestException(this.messageService.getMessage("room.join.fail"));
    }

    @Override
    public String leaveRoom(String api) {
        if (this.userService.leaveRoom(this.userService.getUserByApi(api)))
            return this.messageService.getMessage("room.leave.success");
        throw new BadRequestException(this.messageService.getMessage("room.leave.fail"));
    }

    @Override
    @Transactional
    public String setReady(String api) {
        if (this.userService.setReady(this.userService.getUserByApi(api)))
            return "User is ready!";
        return "Cannot set user ready!";
    }

    @Override
    public FullRoomDTO getRoom(String api) {
        final Room room = this.userService.getUserByApi(api).getRoom();
        if (room != null)
            return this.roomMapper.roomToFullRoomDTO(room);
        throw new NotFoundException(this.messageService.getMessage("room.not.exists"));
    }

}
