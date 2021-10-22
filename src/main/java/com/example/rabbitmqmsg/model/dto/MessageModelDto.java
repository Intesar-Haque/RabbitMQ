package com.example.rabbitmqmsg.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageModelDto {
    String title;
    String body;

    public MessageModelDto() {
    }

    public MessageModelDto(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
