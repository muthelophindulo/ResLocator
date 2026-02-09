package com.RL.webController;

import com.RL.auth.UserEntity;
import com.RL.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ApiController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("add")
    public UserEntity adduser(@RequestBody UserEntity user) throws Exception {
       try{
           userRepository.save(user);
           return user;
       }catch (Exception e){
           throw new Exception("error adding user");
       }
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}
