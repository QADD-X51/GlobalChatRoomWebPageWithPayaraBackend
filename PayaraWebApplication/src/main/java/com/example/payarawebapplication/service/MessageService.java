package com.example.payarawebapplication.service;

import com.example.payarawebapplication.helper.MessageSendObject;
import com.example.payarawebapplication.model.MessageEntity;
import com.example.payarawebapplication.model.UserEntity;
import com.example.payarawebapplication.repository.MessageRepository;
import com.example.payarawebapplication.repository.UserRepository;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.*;

public class MessageService {
    @Inject
    private MessageRepository repository;
    @Inject
    private UserRepository userRepo;

    public List<MessageEntity> getAll() {
        return repository.getAll();
    }

    public MessageEntity getById(Long messageId) {
        return repository.getById(messageId);
    }

    public void insert(MessageEntity message) {
        repository.insert(message);
    }

    public void update(MessageEntity message) {
        repository.update(message);
    }

    public void delete(Long messageId) {
        repository.delete(messageId);
    }

    private boolean validMessage(MessageEntity message) {
        return true;
    }

    public List<MessageEntity> getAllAfterDate(LocalDateTime targetDate) {
        return repository.getAllAfterDate(targetDate);
    }

    public MessageSendObject EntityToSendObject(MessageEntity target)
    {
        return new MessageSendObject(target);
    }

    public List<MessageSendObject> EntityToSendObject(List<MessageEntity> messageList) {
        List<MessageSendObject> toReturn = new ArrayList<>();
        for(MessageEntity messageEntity : messageList) {
            UserEntity userEntity = userRepo.getById(messageEntity.getUser().getID());
            MessageSendObject toSend = new MessageSendObject(messageEntity);
            toSend.setUsername(userEntity.getUsername());
            toReturn.add(toSend);
        }

        return toReturn;
    }

    public boolean insertValid(MessageEntity message) {
        boolean result = this.validMessage(message);
        if(result) {
            this.insert(message);
        }

        return result;
    }
}
