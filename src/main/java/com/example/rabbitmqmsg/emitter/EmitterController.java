package com.example.rabbitmqmsg.emitter;


import com.example.rabbitmqmsg.model.dto.MessageModelDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@Slf4j
@CrossOrigin("*")
public class EmitterController {

    @Autowired
    private EmitterService emitterService;

    @RequestMapping(value = "/test/subscribe", produces = "text/event-stream")
    public SseEmitter subscribe(Authentication authentication) {
        String client = authentication.getName();
        SseEmitter sseEmitter = new SseEmitter(24 * 60 * 60 * 1000L);
        emitterService.addUser(new ConnectedUsers(client,sseEmitter));
        System.out.println(sseEmitter);
        return sseEmitter;
    }

    @RequestMapping("/test/send")
    public ResponseEntity<?> send(Authentication authentication, @RequestParam String request) {
        emitterService.pushNotification(authentication.getName(), request);
        return ResponseEntity.ok().body(emitterService.users);
    }

}
