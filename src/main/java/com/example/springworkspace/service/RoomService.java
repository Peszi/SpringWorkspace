package com.example.springworkspace.service;

import com.example.springworkspace.data.FullRoomDTO;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;

public interface RoomService {
    boolean createRoom(User user);
    boolean deleteRoom(User user);
    boolean startGame(User user);

    Room getRoomById(long roomId);
    Room getRoomByHostId(long hostId);
    Iterable<FullRoomDTO> getAllRooms();
}
