package com.example.payarawebapplication.model;

import com.sun.istack.NotNull;
import jakarta.enterprise.inject.Model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @NotNull
    @Column(unique = true, length = 150, name = "Username")
    private String username;

    @NotNull
    @Column(length = 150, name = "Password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<MessageEntity> messages;

    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }

    public void setUsername(String Username) {
        this.username = Username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getPassword() {
        return password;
    }
}
