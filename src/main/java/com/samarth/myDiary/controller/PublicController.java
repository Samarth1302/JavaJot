package com.samarth.myDiary.controller;

import com.samarth.myDiary.entity.User;
import com.samarth.myDiary.service.UserService;
import com.samarth.myDiary.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authManager;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "100 HP, Damage 0";
    }

    @PostMapping("/signup")
    public void signupUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        try{
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Incorrect user credentials",HttpStatus.BAD_REQUEST);
        }
    }
}
