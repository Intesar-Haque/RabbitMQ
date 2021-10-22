package com.example.rabbitmqmsg.service.impl;

import com.example.rabbitmqmsg.model.MessageModel;
import com.example.rabbitmqmsg.model.dto.MessageModelDto;
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
    public List<MessageModelDto> receiveMsg(String queue) {
        List<MessageModelDto> msgs = new ArrayList<>();
        MessageModelDto obj;
        obj = (MessageModelDto) rabbitTemplate.receiveAndConvert(queue);
        while(Objects.nonNull(obj)){

            msgs.add(obj);
            obj = (MessageModelDto) rabbitTemplate.receiveAndConvert(queue);
        }
        return msgs;
    }
}
