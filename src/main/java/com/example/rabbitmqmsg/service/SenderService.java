package com.example.rabbitmqmsg.service;

import com.example.rabbitmqmsg.model.MessageModel;

public interface SenderService {
    void sendMsg(MessageModel message, String exchange, String routingKey);
}
