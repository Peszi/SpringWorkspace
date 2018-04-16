package com.example.springworkspace.command;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Credentials {

    @NotNull(message = "Param 'username' is needed!!!!")
    @NotEmpty(message = "Username cannot be empty!!!!")
    private String username;

    @NotNull(message = "Param 'password' is needed!!!!")
    @NotEmpty(message = "Password cannot be empty!!!")
    private String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isInit() {
        if (this.username != null && this.password != null)
            return true;
        return false;
    }
}
