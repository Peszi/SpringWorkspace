package com.example.springworkspace.service;

import com.example.springworkspace.data.FullRoomDTO;

public interface UserRoomService {
    String joinRoom(String api, long roomId);
    String leaveRoom(String api);
    String setReady(String api);
    FullRoomDTO getRoom(String api);
}
