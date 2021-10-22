package com.example.rabbitmqmsg.service;

import com.example.rabbitmqmsg.model.dto.MessageModelDto;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public interface ReceiverService {
    List<MessageModelDto> receiveMsg(String queue);
}
