package com.example.rabbitmqmsg.controller;


import com.example.rabbitmqmsg.model.Users;
import com.example.rabbitmqmsg.repo.UsersRepo;
import com.example.rabbitmqmsg.service.RabbitQueueService;
import com.example.rabbitmqmsg.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Objects;

@Controller
public class HomeController {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    UsersService usersService;


    @RequestMapping("/loggedIn")
    public String home (Authentication authentication) throws  NullPointerException{
        String role = usersService.findRole(authentication.getName());
        if(Objects.equals(role, "ADMIN")){
            return "redirect:/admin";
        } else if(Objects.equals(role, "MERCHANT")){
            return "redirect:/merchant";

        } else {
            return "redirect:/customer";
        }
    }
    @RequestMapping("/")
    public String home (){
            return "index";
    }

    @RequestMapping("/test")
    public String test () {

//        queueService.addQueueToListener(exchange, queue);
        return null;
    }
}
