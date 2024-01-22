package com.example.payarawebapplication.service.interfaces;

import com.example.payarawebapplication.helper.SimpleUser;
import com.example.payarawebapplication.model.UserEntity;

public interface UserService extends BaseService<UserEntity> {
    public UserEntity getByUserCredentials(UserEntity userEntity);

    public String insertValid(UserEntity user);

    public UserEntity SimpleUserToEntity(SimpleUser user);
}
