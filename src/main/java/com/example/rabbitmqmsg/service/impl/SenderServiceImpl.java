package com.example.rabbitmqmsg.service.impl;

import com.example.rabbitmqmsg.model.MessageModel;
import com.example.rabbitmqmsg.model.dto.MessageModelDto;
import com.example.rabbitmqmsg.service.SenderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class SenderServiceImpl implements SenderService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void sendMsg(MessageModel messageModel) {
        MessageModelDto message = new MessageModelDto(messageModel.getTitle(), messageModel.getBody());
        rabbitTemplate.convertAndSend(messageModel.getExchange(), messageModel.getRoutingKey(), message);
    }
}
