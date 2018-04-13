package com.example.springworkspace.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomListDTO {
    Iterable<RoomDTO> usersList;
}
