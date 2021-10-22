package com.example.rabbitmqmsg.service;

import com.example.rabbitmqmsg.model.dto.UsersDto;
import org.springframework.http.ResponseEntity;

public interface UsersService {
    ResponseEntity<String> addUser(UsersDto usersDto);
    String findRole(String uname);
}
