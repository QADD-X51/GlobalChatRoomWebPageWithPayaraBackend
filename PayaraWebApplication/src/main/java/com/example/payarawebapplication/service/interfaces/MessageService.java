package com.example.payarawebapplication.service.interfaces;

import com.example.payarawebapplication.helper.MessageSendObject;
import com.example.payarawebapplication.model.MessageEntity;
import jakarta.ejb.Remote;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageService extends BaseService<MessageEntity> {
    public List<MessageEntity> getAllAfterDate(LocalDateTime targetDate);

    public MessageSendObject EntityToSendObject(MessageEntity target);

    public List<MessageSendObject> EntityToSendObject(List<MessageEntity> messageList);

    public boolean insertValid(MessageEntity message);
}
