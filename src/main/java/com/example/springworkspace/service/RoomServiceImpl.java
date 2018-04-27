package com.example.springworkspace.service;

import com.example.springworkspace.data.FullRoomDTO;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.mapper.RoomMapper;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;
import com.example.springworkspace.repository.RoomRepository;
import com.example.springworkspace.service.util.AuditLoggerService;
import com.example.springworkspace.service.util.MessageService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private AuditLoggerService loggerService;
    private MessageService messageService;

    private RoomMapper roomMapper;
    private RoomRepository roomRepository;

    public RoomServiceImpl(AuditLoggerService loggerService, MessageService messageService, RoomMapper roomMapper, RoomRepository roomRepository) {
        this.loggerService = loggerService;
        this.messageService = messageService;
        this.roomMapper = roomMapper;
        this.roomRepository = roomRepository;
    }

    @Override
    public boolean createRoom(User user) {
        this.validateRoomExists(user.getId());
        Room room = new Room(user.getId());
        this.roomRepository.save(room);
        if (this.roomRepository.findRoomByHostId(user.getId()).isPresent()) {
            this.loggerService.roomCreated("", room.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRoom(User user) {
        Room room = this.validateAndGetRoomWithHostId(user.getId());
        this.roomRepository.delete(room);
        if (!this.roomRepository.findRoomByHostId(user.getId()).isPresent()) {
            this.loggerService.roomDeleted("", room.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean startGame(User user) {
        Room room = this.validateAndGetRoomWithHostId(user.getId());
        if (!room.getStarted()) {
            room.setStarted(true);
            this.roomRepository.save(room);
            return true;
        }
        return false;
    }

    @Override
    public Room getRoomById(long roomId) {
        return this.validateAndGetRoom(roomId);
    }

    @Override
    public Room getRoomByHostId(long hostId) {
        return this.validateAndGetRoomWithHostId(hostId);
    }

    @Override
    public Iterable<FullRoomDTO> getAllRooms() {
        return this.roomRepository.findAll()
                .stream()
                .map(this.roomMapper::roomToFullRoomDTO)
                .collect(Collectors.toList());
    }

    // Validate

    private Room validateAndGetRoom(long roomId) {
        return this.roomRepository.findById(roomId).orElseThrow(
                () -> new NotFoundException(this.messageService.getMessage("room.not.found")));
    }

    private Room validateAndGetRoomWithHostId(long hostId) {
        return this.roomRepository.findRoomByHostId(hostId).orElseThrow(
                () -> new NotFoundException(this.messageService.getMessage("room.not.found")));
    }

    private void validateRoomExists(long hostId) {
        this.roomRepository.findRoomByHostId(hostId).ifPresent(
                room -> { throw new NotFoundException(this.messageService.getMessage("room.already.exists")); });
    }
}
