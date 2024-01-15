package com.example.payarawebapplication.helper;

import com.example.payarawebapplication.model.MessageEntity;

import java.time.LocalDateTime;

public class MessageSendObject {
    private String message;
    private LocalDateTime date;
    private String username;

    public MessageSendObject(MessageEntity messageEntity) {
        this.message = messageEntity.getMessage();
        this.date = messageEntity.getDate();
        this.username = messageEntity.getUser().getUsername();
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
