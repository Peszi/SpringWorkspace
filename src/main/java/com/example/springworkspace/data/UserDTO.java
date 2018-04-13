package com.example.springworkspace.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserDTO {
    private long id;
    private Boolean inGame;

}
