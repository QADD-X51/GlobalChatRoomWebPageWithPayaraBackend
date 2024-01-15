package com.example.payarawebapplication.service;

import com.example.payarawebapplication.helper.SimpleUser;
import com.example.payarawebapplication.model.UserEntity;
import com.example.payarawebapplication.repository.UserRepository;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;

import java.util.List;
import java.util.regex.Pattern;

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

    public UserEntity getByUserCredentials(UserEntity userEntity) { return userRepo.getByUserCredentials(userEntity); }

    private boolean usernameTaken(String username) {
        UserEntity user = userRepo.getByUsername(username);
        return user != null;
    }

    private String ValidUser(UserEntity user) {
        if(!Pattern.matches("[a-zA-Z0-9_-]+",user.getUsername())) return "Username can only contain normal characters, minus and underscore.";
        if(!Pattern.matches("[a-zA-Z0-9_-]+",user.getPassword())) return "Password can only contain normal characters, minus and underscore.";

        if(user.getUsername().length() > 150) return "Username too long.";
        if(user.getPassword().length() > 150) return "Password too long.";

        if(user.getUsername().length() < 5) return "Username too short long.";
        if(user.getPassword().length() < 5) return "Password too short long.";

        if(this.usernameTaken(user.getUsername())) return "Username taken.";

        return "Ok";
    }

    public String insertValid(UserEntity user) {
        String validationString = this.ValidUser(user);
        if(validationString.equals("Ok")) this.insert(user);;

        return validationString;
    }

    public UserEntity SimpleUserToEntity(SimpleUser user){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(user.getPassword());
        userEntity.setUsername(user.getUsername());

        return userEntity;
    }

}
