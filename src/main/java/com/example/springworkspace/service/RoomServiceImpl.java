package com.example.springworkspace.service;

import com.example.springworkspace.data.RoomDTO;
import com.example.springworkspace.data.RoomListDTO;
import com.example.springworkspace.mapper.RoomMapper;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;
import com.example.springworkspace.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomMapper roomMapper;
    private RoomRepository roomRepository;

    public RoomServiceImpl(RoomMapper roomMapper, RoomRepository roomRepository) {
        this.roomMapper = roomMapper;
        this.roomRepository = roomRepository;
    }

    @Override
    public boolean isRoomExits(long roomId) {
        return this.roomRepository.existsById(roomId);
    }

    @Override
    public Optional<RoomDTO> createRoom(User user) {
        if (!this.isRoomExists(user.getId()).isPresent()) {
            Room room = new Room(user.getId());
            this.roomRepository.save(room);
            room.addUser(user);
            this.roomRepository.flush();
            return Optional.of(this.roomMapper.roomToRoomDTO(room));
        }
        return Optional.empty();
    }

    @Override
    public boolean removeRoom(long hostId) {
        Optional<Room> room = this.isRoomExists(hostId);
        if (room.isPresent()) {
            this.roomRepository.delete(room.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Room> getRawRoomById(long roomId) {
        return this.roomRepository.findById(roomId);
    }

    @Override
    public Optional<RoomDTO> getRoomById(long roomId) {
        Optional<Room> room = this.roomRepository.findById(roomId);
        if (room.isPresent())
            return Optional.of(this.roomMapper.roomToRoomDTO(room.get()));
        return Optional.empty();
    }

    @Override
    public Iterable<RoomDTO> getAllRooms() {
        return this.roomRepository.findAll()
                .stream()
                .map(this.roomMapper::roomToRoomDTO)
                .collect(Collectors.toList());
    }

    private Optional<Room> isRoomExists(long hostId) {
        return this.roomRepository.findRoomByHostId(hostId);
    }
}
