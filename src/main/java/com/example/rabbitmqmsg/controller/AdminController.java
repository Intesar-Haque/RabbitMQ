package com.example.rabbitmqmsg.controller;

import com.example.rabbitmqmsg.model.MessageModel;
import com.example.rabbitmqmsg.model.dto.UsersDto;
import com.example.rabbitmqmsg.service.ReceiverService;
import com.example.rabbitmqmsg.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    SenderService rabbitMQSender;
    @RequestMapping
    public String admin(){
        return "admin";
    }
    @RequestMapping("/message")
    public String msg(Model model){
        model.addAttribute("message", new MessageModel());
        return "sendMessage";
    }
    @RequestMapping("/message/send")
    public String sendMsg(@ModelAttribute("message") MessageModel message){
        String uname ="intesar2";
        rabbitMQSender.sendMsg(message, uname);
        return "redirect:/admin";
    }

}
