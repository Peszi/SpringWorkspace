package com.example.springworkspace.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserListDTO {
    Iterable<UserDTO> usersList;
}
