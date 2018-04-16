package com.example.springworkspace.service;

import com.example.springworkspace.data.FullRoomDTO;
import com.example.springworkspace.data.RoomDTO;
import com.example.springworkspace.data.RoomListDTO;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;

import java.util.Optional;

public interface RoomService {
    String createRoom(final String ip, String api);
    String deleteRoom(final String ip, String api);
    Iterable<FullRoomDTO> getAllRooms();
    Room getRoomById(long roomId);
}
