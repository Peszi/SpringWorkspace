package com.example.springworkspace.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {
    private long id;
    private Boolean inGame;

}
