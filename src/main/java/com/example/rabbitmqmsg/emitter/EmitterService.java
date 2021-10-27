package com.example.rabbitmqmsg.emitter;

import com.example.rabbitmqmsg.model.dto.MessageModelDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmitterService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    List<ConnectedUsers> users = new ArrayList<>();

    public void addUser(ConnectedUsers user) {
        user.getEmitter().onCompletion(() -> users.remove(user));
        user.getEmitter().onTimeout(() -> users.remove(user));
        users.add(user);
    }

    public void pushNotification(String username, String request) {
        List<ConnectedUsers> disconnectedUsers = new ArrayList<>();
        users.forEach(user -> {
            if(isConnected(user.getLoginId())){
                try {
                    user.getEmitter().send(SseEmitter
                            .event()
                            .name(username)
                            .data(request));

                } catch (IOException e) {
                    disconnectedUsers.add(user);
                }
            }
            else {
                System.out.println("Message not Sent");
            }
        });

        users.removeAll(disconnectedUsers);
    }


    private boolean isConnected(String loginId){
        for (ConnectedUsers users : users){
            if (users.getLoginId().equals(loginId))
                return true;
        }
        return false;
    }
}