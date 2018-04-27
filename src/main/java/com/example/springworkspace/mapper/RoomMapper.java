package com.example.springworkspace.mapper;

import com.example.springworkspace.data.FullRoomDTO;
import com.example.springworkspace.data.RoomDTO;
import com.example.springworkspace.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDTO roomToRoomDTO(Room room);

    @Mapping(source = "users", target = "usersList")
    FullRoomDTO roomToFullRoomDTO(Room room);
}
