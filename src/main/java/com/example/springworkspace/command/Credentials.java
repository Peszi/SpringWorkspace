package com.example.springworkspace.command;

import com.example.springworkspace.trash.net.Param;

import javax.validation.constraints.NotEmpty;

public class Credentials {

    @NotEmpty
    private String username;

    @NotEmpty
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

    public Param getUsernameParam() {
        return new Param("username", this.username);
    }

    public Param getPasswordParam() {
        return new Param("password", this.password);
    }
}
