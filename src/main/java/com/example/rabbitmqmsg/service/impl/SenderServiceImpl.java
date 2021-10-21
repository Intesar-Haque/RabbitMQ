package com.example.rabbitmqmsg.service.impl;

import com.example.rabbitmqmsg.model.MessageModel;
import com.example.rabbitmqmsg.service.SenderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class SenderServiceImpl implements SenderService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${rabbitmqmsg.rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmqmsg.rabbitmq.routingkey}")
    private String routingkey;


    @Override
    public void sendMsg(MessageModel message) {
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
        System.out.println(message.getName()+" sent: " + message.getMsg());
    }
}
