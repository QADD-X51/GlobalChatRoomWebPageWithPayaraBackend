package com.example.payarawebapplication.resouce;

import com.example.payarawebapplication.helper.MessageRecievedObject;
import com.example.payarawebapplication.helper.MessageSendObject;
import com.example.payarawebapplication.model.MessageEntity;
import com.example.payarawebapplication.model.UserEntity;
import com.example.payarawebapplication.service.interfaces.MessageService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;
import java.util.List;

@Path("message")
public class MessageResource {

    @EJB
    private MessageService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageEntity> getAllMessages() {
        return service.getAll();
    }

    @GET
    @Path("/id/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MessageEntity getUserById(@PathParam("messageId") Long messageId) {
        return service.getById(messageId);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageSendObject> getAllAfterDateObj() {
        return service.EntityToSendObject(service.getAll());
    }

    //Example string "2015-01-28T20:51:28.609"
    @GET
    @Path("/{dateString}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageSendObject> getAllAfterDateObj(@PathParam("dateString") String dateString ) {

        LocalDateTime targetDate = LocalDateTime.parse(dateString);
        return service.EntityToSendObject(service.getAllAfterDate(targetDate));
    }

    @POST
    @Path(("/add"))
    @Consumes(MediaType.APPLICATION_JSON)
    public void addMessage(MessageRecievedObject messageReceived) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(messageReceived.getMessage());
        messageEntity.setDate(LocalDateTime.now());
        UserEntity user = new UserEntity();
        user.setID(messageReceived.getSenderId());
        messageEntity.setUser(user);

        service.insert(messageEntity);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createMessage(MessageEntity message) {
        service.insert(message);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateMessage(MessageEntity message) {
        service.update(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void delete(@PathParam("messageId") Long messageId) {
        service.delete(messageId);
    }


}
