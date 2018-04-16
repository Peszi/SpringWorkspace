package com.example.springworkspace.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UserListDTO {
    Iterable<UserDTO> usersList;
}
