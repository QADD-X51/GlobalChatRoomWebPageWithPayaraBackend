package com.example.payarawebapplication.resouce;

import com.example.payarawebapplication.helper.LoginObject;
import com.example.payarawebapplication.helper.SimpleUser;
import com.example.payarawebapplication.model.UserEntity;
import com.example.payarawebapplication.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    @Path("/id/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity getUserById(@PathParam("userId") Long userId) {
        return userService.getById(userId);
    }

    @GET
    @Path("/login/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public LoginObject login(@PathParam("username") String username, @PathParam("password") String password) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(username);
        userEntity.setPassword(password);

        LoginObject toReturn = new LoginObject();
        UserEntity returnedUser = userService.getByUserCredentials(userEntity);
        if(returnedUser == null) {
            toReturn.setUserId(-1);
            toReturn.setValidationResult("Wrong credentials.");
            return toReturn;
        }
        toReturn.setUserId(returnedUser.getID());
        toReturn.setValidationResult("Ok");

        return toReturn;
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserEntity userEntity) {

        String validationResult = userService.insertValid(userEntity);
        if(validationResult.equals("Ok")) {
            return Response.status(Response.Status.CREATED)
                    .entity("User registered successfully.")
                    .build();
        }

        return Response.status(Response.Status.CONFLICT)
                .entity(validationResult)
                .build();
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
