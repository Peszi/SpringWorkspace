package com.example.springworkspace.model;

import com.example.springworkspace.configuration.BasicConfiguration;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private Boolean isReady;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    @JsonManagedReference
    private Room room;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isReady = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
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

    public Boolean getIsReady() {
        return isReady;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean hasRoom() {
        return this.room != null ? true : false;
    }

    public void setRoom(Room room) {
        this.room = room;
//        room.addUser(this);
        this.isReady = false;
    }

    public void removeRoom() {
        this.isReady = false;
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
}