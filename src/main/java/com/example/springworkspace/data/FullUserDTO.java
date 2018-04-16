package com.example.springworkspace.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class FullUserDTO extends UserDTO {

    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    private String apiKey;
    private String createdAt;

    public void setCreatedAt(Date createdAt) {
        this.createdAt = new SimpleDateFormat(FullUserDTO.DATE_FORMAT).format(createdAt);
    }
}
