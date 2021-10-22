package com.example.rabbitmqmsg.controller;

import com.example.rabbitmqmsg.model.dto.UsersDto;
import com.example.rabbitmqmsg.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UsersController {
    @Autowired
    UsersService usersService;
    @PostMapping( "/registration/register")
    public String registerUser(@ModelAttribute("usersDto") UsersDto usersDto) {
        usersService.addUser(usersDto);
        return "redirect:/loggedIn";
    }
    @RequestMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("usersDto", new UsersDto());
        return "registration";
    }
}
