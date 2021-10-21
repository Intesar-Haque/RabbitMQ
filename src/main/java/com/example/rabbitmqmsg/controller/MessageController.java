package com.example.rabbitmqmsg.controller;

import com.example.rabbitmqmsg.model.MessageModel;
import com.example.rabbitmqmsg.service.ReceiverService;
import com.example.rabbitmqmsg.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    SenderService rabbitMQSender;
    @Autowired
    ReceiverService receiverService;

    @RequestMapping("/sendMsg")
    public String sendMsg(@RequestBody MessageModel messageModel){
        rabbitMQSender.sendMsg(messageModel);
        return "Msg sent";
    }
    @RequestMapping("/receiveMsg")
    public List<Object> receiveMsg(@RequestParam String queue){
        return  receiverService.receiveMsg(queue);
    }
}
