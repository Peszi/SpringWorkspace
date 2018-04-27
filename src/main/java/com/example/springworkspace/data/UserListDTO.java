package com.example.springworkspace.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UserListDTO {
    Iterable<UserDTO> usersList;
}
