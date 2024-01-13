package com.example.payarawebapplication.service;

import com.example.payarawebapplication.model.UserEntity;
import com.example.payarawebapplication.repository.UserRepository;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;

import java.util.List;

public class UserService {

    @Inject
    private UserRepository userRepo;

    public List<UserEntity> getAll() {
        return userRepo.getAll();
    }

    public UserEntity getById(Long userId) {
        return userRepo.getById(userId);
    }

    public void insert(UserEntity user) {
        userRepo.insert(user);
    }

    public void update(UserEntity user) {
        userRepo.update(user);
    }

    public void delete(Long userId) {
        userRepo.delete(userId);
    }

    private boolean usernameTaken(String username)
    {
        UserEntity user = userRepo.getByUsername(username);
        return user != null;
    }

    private String ValidUser(UserEntity user)
    {
        if(this.usernameTaken(user.getUsername())) return "Username taken.";

        if(user.getUsername().length() > 150) return "Username too long.";
        if(user.getPassword().length() > 150) return "Password too long.";

        if(user.getUsername().length() < 5) return "Username too short long.";
        if(user.getPassword().length() < 5) return "Password too short long.";

        return "Ok";
    }

}
