package com.example.springworkspace.model;

import com.example.springworkspace.configuration.BasicConfiguration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(length = 60)
    private String password;

    @Column(length = BasicConfiguration.API_KEY_LENGTH)
    private String apiKey;

    @ManyToOne(targetEntity = Room.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @JsonManagedReference
    private Room room;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setRoom(Room room) {
        room.addUser(this);
        this.room = room;
    }

    public void removeRoom() {
        if (this.room != null)
            this.room.removeUser(this);
        this.room = null;
    }

    public Room getRoom() {
        return room;
    }

    public boolean hasRoom() {
        return this.room != null ? true : false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", room=" + room +
                '}';
    }
}