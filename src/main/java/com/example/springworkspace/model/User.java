package com.example.springworkspace.model;

import com.example.springworkspace.configuration.BasicConfiguration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
//@JsonInclude(JsonInclude.Include.NON_NULL)
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

    private Boolean inGame;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    //@ManyToOne//(targetEntity = Room.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "room_id", referencedColumnName = "id")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    //@JoinColumn(name = "fk_room")
    @JsonManagedReference
    private Room room;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.inGame = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Boolean getInGame() {
        return inGame;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean hasRoom() {
        return this.room != null ? true : false;
    }

    public void setRoom(Room room) {
        this.room = room;
        room.addUser(this);
        this.inGame = true;
    }

    public void removeRoom() {
        this.inGame = false;
//        if (this.room != null)
//            this.room.removeUser(this);
        this.room = null;
    }

    public Room getRoom() {
        return room;
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