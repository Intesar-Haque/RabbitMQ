package com.example.rabbitmqmsg.service.impl;

import com.example.rabbitmqmsg.service.RabbitQueueService;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitQueueServiceImpl implements RabbitQueueService {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    public RabbitQueueServiceImpl() {
    }

    @Override
    public void addNewQueue(String queueName, String exchangeName, String routingKey) {
        Queue queue = new Queue(queueName, true, false, false);
        Binding binding = new Binding(
                queueName,
                Binding.DestinationType.QUEUE,
                exchangeName,
                routingKey,
                null
        );
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
    }

}
