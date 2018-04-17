package com.example.springworkspace.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class RoomDTO {
    private Long id;
    private Long hostId;
    private int usersCount;
    private int usersReadyCount;
    private boolean isStarted;
}
