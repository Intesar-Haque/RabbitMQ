package com.example.rabbitmqmsg.service.impl;

import com.example.rabbitmqmsg.model.Users;
import com.example.rabbitmqmsg.model.dto.UsersDto;
import com.example.rabbitmqmsg.model.enums.UsersRole;
import com.example.rabbitmqmsg.repo.UsersRepo;
import com.example.rabbitmqmsg.service.RabbitQueueService;
import com.example.rabbitmqmsg.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServiceImp implements UsersService, UserDetailsService {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    RabbitQueueService queueService;

    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<String> addUser(UsersDto usersDto) {
        boolean userExists = usersRepo.findByUname(usersDto.getUname()).isPresent();
        if(userExists){
            return new ResponseEntity<>("Username already taken", HttpStatus.IM_USED);
        } else{

            //Adding user to rabbitMQ
            String username = usersDto.getUname();
            String role = usersDto.getRole().toString();
            queueService.addNewQueue(role,role,username);
            //Adding user to mySQL database
            Users user = new Users();
            user.setUname(username);
            user.setUsersRole(usersDto.getRole());
            user.setName(usersDto.getName());
            String encodedPassword = passwordEncoder.encode(usersDto.getPass());
            user.setPass(encodedPassword);
            usersRepo.save(user);

            return  new ResponseEntity<>("User Registered", HttpStatus.OK);
        }

    }

    @Override
    public String findRole(String uname) {
        Optional<Users> usersCollection = usersRepo.findByUname(uname);
        if(usersCollection.isPresent()){
            Users user = usersCollection.get();
            UsersRole role = user.getUsersRole();
            return  role.name();
        } else{
            return null;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
        return usersRepo.findByUname(uname).orElseThrow( () -> new UsernameNotFoundException("User doesn't Exist"));
    }

}
