package com.example.rabbitmqmsg.controller;

import com.example.rabbitmqmsg.model.dto.MessageModelDto;
import com.example.rabbitmqmsg.service.ReceiverService;
import com.example.rabbitmqmsg.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    ReceiverService receiverService;
    @Autowired
    UsersService usersService;

    @RequestMapping
    public String customer(){
        return "customer";
    }

    @RequestMapping("/messages")
    public String messages(Model model, Authentication authentication){
        String role = usersService.findRole(authentication.getName());
        List<MessageModelDto> messages = receiverService.receiveMsg(role);
        model.addAttribute("messages",messages);
        return "receiveMessage";
    }

}
