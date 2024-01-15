package com.example.payarawebapplication.helper;

public class MessageRecievedObject {
    private String message;
    private long senderId;

    public String getMessage() {
        return message;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }
}
