package com.example.rabbitmqmsg.emitter;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Data
@AllArgsConstructor
public class ConnectedUsers {
    private String loginId;
    private SseEmitter emitter;
}
