package com.samarth.myDiary.controller;

import com.samarth.myDiary.entity.User;
import com.samarth.myDiary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

}
