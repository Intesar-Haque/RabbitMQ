package com.example.rabbitmqmsg.service;

public interface RabbitQueueService {
    void addNewQueue(String queueName,String exchangeName,String routingKey);
}
