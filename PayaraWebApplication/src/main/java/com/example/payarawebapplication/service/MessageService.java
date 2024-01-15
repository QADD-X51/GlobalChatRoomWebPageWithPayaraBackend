package com.example.payarawebapplication.service;

import com.example.payarawebapplication.model.MessageEntity;
import com.example.payarawebapplication.repository.MessageRepository;
import jakarta.inject.Inject;

import java.util.List;

public class MessageService {
    @Inject
    private MessageRepository repository;

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

    private boolean ValidMessage(MessageEntity message) {


        return true;
    }
}
