package com.example.springworkspace.service;

import com.example.springworkspace.data.FullRoomDTO;
import com.example.springworkspace.data.RoomDTO;
import com.example.springworkspace.exception.NotFoundException;
import com.example.springworkspace.mapper.RoomMapper;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.repository.RoomRepository;
import com.example.springworkspace.service.util.AuditLoggerService;
import com.example.springworkspace.service.util.MessageService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private AuditLoggerService loggerService;
    private MessageService messageService;

    private RoomMapper roomMapper;

    private UserService userService;
    private RoomRepository roomRepository;

    public RoomServiceImpl(AuditLoggerService loggerService, MessageService messageService, RoomMapper roomMapper, UserService userService, RoomRepository roomRepository) {
        this.loggerService = loggerService;
        this.messageService = messageService;
        this.roomMapper = roomMapper;
        this.userService = userService;
        this.roomRepository = roomRepository;
    }

    @Override
    public String createRoom(final String ip, String api) {
        final long hostId = this.userService.getUserByApi(api).getId();
        this.validateRoomExists(hostId);
        Room room = new Room(hostId);
        this.roomRepository.save(room);
        if (this.roomRepository.findRoomByHostId(hostId).isPresent()) {
            this.loggerService.roomCreated(ip, room.getId());
            return this.messageService.getMessage("room.create.success");
        }
        return this.messageService.getMessage("room.create.fail");
    }

    @Override
    public String deleteRoom(final String ip, String api) {
        final long hostId = this.userService.getUserByApi(api).getId();
        Room room = this.validateAndGetRoomWithHostId(hostId);
        this.roomRepository.delete(room);
        if (!this.roomRepository.findRoomByHostId(hostId).isPresent()) {
            this.loggerService.roomDeleted(ip, room.getId());
            return this.messageService.getMessage("room.delete.success");
        }
        return this.messageService.getMessage("room.delete.fail");
    }

    @Override
    public Iterable<FullRoomDTO> getAllRooms() {
        return this.roomRepository.findAll()
                .stream()
                .map(this.roomMapper::roomToFullRoomDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Room getRoomById(long roomId) {
        return this.validateAndGetRoom(roomId);
    }

    //    @Override
//    public Optional<Room> getRawRoomById(long roomId) {
//        return this.roomRepository.findById(roomId);
//    }
//
//    @Override
//    public Optional<RoomDTO> getRoomById(long roomId) {
//        Optional<Room> room = this.roomRepository.findById(roomId);
//        if (room.isPresent())
//            return Optional.of(this.roomMapper.roomToRoomDTO(room.get()));
//        return Optional.empty();
//    }
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
