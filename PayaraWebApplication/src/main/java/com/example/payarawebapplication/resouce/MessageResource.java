package com.example.payarawebapplication.resouce;

import com.example.payarawebapplication.model.MessageEntity;
import com.example.payarawebapplication.model.UserEntity;
import com.example.payarawebapplication.service.MessageService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("message")
public class MessageResource {
    @Inject
    private MessageService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageEntity> getAllMessages() {
        return service.getAll();
    }

    @GET
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MessageEntity getUserById(@PathParam("messageId") Long messageId) {
        return service.getById(messageId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(MessageEntity message) {
        service.insert(message);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(MessageEntity message) {
        service.update(message);
    }

    @DELETE
    @Path("/{userId}")
    public void delete(@PathParam("userId") Long messageId) {
        service.delete(messageId);
    }
}
