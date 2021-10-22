package com.example.rabbitmqmsg.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageModel {
    String title;
    String body;
    String routingKey = "";
    String exchange;
}
