package com.example.springworkspace.mapper;

import com.example.springworkspace.data.FullRoomDTO;
import com.example.springworkspace.data.FullUserDTO;
import com.example.springworkspace.data.RoomDTO;
import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDTO roomToRoomDTO(Room room);

    @Mapping(source = "users", target = "usersList")
    FullRoomDTO roomToFullRoomDTO(Room room);
}
