package com.example.payarawebapplication.resouce;

import com.example.payarawebapplication.model.UserEntity;
import com.example.payarawebapplication.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("user")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserEntity> getAllUsers() {
        return userService.getAll();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity getUserById(@PathParam("userId") Long userId) {
        return userService.getById(userId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(UserEntity user) {
        userService.insert(user);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(UserEntity user) {
        userService.update(user);
    }

    @DELETE
    @Path("/{userId}")
    public void delete(@PathParam("userId") Long userId) {
        userService.delete(userId);
    }
}
