package com.example.rabbitmqmsg.repo;

import com.example.rabbitmqmsg.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
//    List<Users> findByNameAndContact(String name, String  contact);
//
    Users findByUserId(Long id);
   // Users findByUname(String uname;)

    Optional<Users> findByUname(String uname);

    @Transactional
    @Modifying
    void deleteByUserId(Long id);

}
