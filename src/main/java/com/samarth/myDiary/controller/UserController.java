package com.samarth.myDiary.controller;

import com.samarth.myDiary.entity.User;
import com.samarth.myDiary.repository.UserRepo;
import com.samarth.myDiary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User userInDB = userService.findByUsername(username);

        userInDB.setUsername(user.getUsername());
        userInDB.setPassword(user.getPassword());
        userService.saveUser(userInDB);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(auth.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
