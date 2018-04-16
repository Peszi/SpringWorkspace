package com.example.springworkspace.service;

import com.example.springworkspace.data.FullRoomDTO;
import com.example.springworkspace.data.RoomDTO;
import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.mapper.RoomMapper;
import com.example.springworkspace.mapper.UserMapper;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoomServiceImpl implements UserRoomService {

    private UserMapper userMapper;
    private RoomMapper roomMapper;

    private UserService userService;
    private RoomService roomService;

    public UserRoomServiceImpl(UserMapper userMapper, RoomMapper roomMapper, UserService userService, RoomService roomService) {
        this.userMapper = userMapper;
        this.roomMapper = roomMapper;
        this.userService = userService;
        this.roomService = roomService;
    }

    @Override
    public String joinRoom(String api, long roomId) {
        if (this.userService.joinRoom(api, this.roomService.getRoomById(roomId)))
            return "Joined the room!";
        return "You are already in room!";
    }

    @Override
    public String leaveRoom(String api) {
        if (this.userService.leaveRoom(api))
            return "Leaved the room!";
        return "You aren't in any room now!";
    }

    @Override
    public FullRoomDTO getRoom(String api) {
        final Room room = this.userService.getUserByApi(api).getRoom();
        if (room != null) {
//            FullRoomDTO roomDTO = new FullRoomDTO();
//            roomDTO.setId(room.getId());
//            roomDTO.setHostId(room.getHostId());
//            roomDTO.setUsersCount(room.getUsersCount());
//
//            List<UserDTO> userDTOIterable = new ArrayList<>();
//            room.getUsers().forEach(user -> {userDTOIterable.add(this.userMapper.userToUserDTO(user));});
//            roomDTO.setUsersList(userDTOIterable);

            return this.roomMapper.roomToFullRoomDTO(room);
        }
        throw new NotFoundException("You aren't in any room now!");
    }
}
