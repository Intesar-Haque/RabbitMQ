package com.example.rabbitmqmsg.model.dto;

import com.example.rabbitmqmsg.model.enums.UsersRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
    String name;
    String pass;
    String uname;
    UsersRole role;
}
