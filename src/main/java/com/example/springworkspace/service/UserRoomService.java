package com.example.springworkspace.service;

import com.example.springworkspace.data.FullRoomDTO;
import com.example.springworkspace.model.Room;

public interface UserRoomService {
    String joinRoom(String api, long roomId);
    String leaveRoom(String api);
    FullRoomDTO getRoom(String api);
}
