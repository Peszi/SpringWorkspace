package com.example.springworkspace.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    private Long hostId;

    private Boolean isStarted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room", targetEntity = User.class, orphanRemoval = true)
    @JsonBackReference
    private Set<User> users = new HashSet<>();

    public Room(Long hostId) {
        this.hostId = hostId;
        this.isStarted = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public void setStarted(Boolean started) {
        isStarted = started;
    }

    public Long getId() {
        return id;
    }

    public Long getHostId() {
        return hostId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public int getUsersCount() {
        return this.users.size();
    }

    public int getUsersReadyCount() {
        return (int) this.users.stream().filter(User::getIsReady).count();
    }

    public Boolean getStarted() {
        return isStarted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id != null ? id.equals(room.id) : room.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
