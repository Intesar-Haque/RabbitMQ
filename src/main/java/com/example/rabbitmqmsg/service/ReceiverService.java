package com.example.rabbitmqmsg.service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public interface ReceiverService {
    List<Object> receiveMsg(String queue);
}
