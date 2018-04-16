package com.example.springworkspace.mapper;

import com.example.springworkspace.data.FullUserDTO;
import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    FullUserDTO userToFullUserDTO(User user);
}
