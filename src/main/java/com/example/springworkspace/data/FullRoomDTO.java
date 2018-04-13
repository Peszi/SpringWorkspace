package com.example.springworkspace.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FullRoomDTO extends RoomDTO {
    Iterable<UserDTO> usersList;
}
