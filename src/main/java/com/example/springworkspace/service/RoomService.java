package com.example.springworkspace.service;

import com.example.springworkspace.data.RoomDTO;
import com.example.springworkspace.data.RoomListDTO;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;

import java.util.Optional;

public interface RoomService {
    boolean isRoomExits(long roomId);
    Optional<RoomDTO> createRoom(User user);
    boolean removeRoom(long hostId);
    Optional<RoomDTO> getRoomById(long roomId);
    Optional<Room> getRawRoomById(long roomId);
    Iterable<RoomDTO> getAllRooms();
}
