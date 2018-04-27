package com.example.springworkspace.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FullRoomDTO extends RoomDTO {
    Set<UserDTO> usersList;
}
