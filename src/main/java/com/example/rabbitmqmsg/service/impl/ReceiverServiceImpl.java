package com.example.rabbitmqmsg.service.impl;

import com.example.rabbitmqmsg.service.ReceiverService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReceiverServiceImpl implements ReceiverService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public List<Object> receiveMsg(String queue) {
        List<Object> msgs = new ArrayList<>();
        Object obj;
        obj = rabbitTemplate.receiveAndConvert(queue);
        while(Objects.nonNull(obj)){
            msgs.add(obj);
            obj = rabbitTemplate.receiveAndConvert(queue);
        }
        return msgs;
    }
}
