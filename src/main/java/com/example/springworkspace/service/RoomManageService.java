package com.example.springworkspace.service;

public interface RoomManageService {
    String createRoom(String api);
    String deleteRoom(String api);
    String kickUser(String api, long userId);
    String startGame(String api);
}
