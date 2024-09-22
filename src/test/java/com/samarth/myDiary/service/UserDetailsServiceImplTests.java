package com.samarth.myDiary.service;

import com.samarth.myDiary.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collections;

public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        com.samarth.myDiary.entity.User mockUser = new com.samarth.myDiary.entity.User();
        mockUser.setUsername("sam");
        mockUser.setPassword("ivbwr");
        mockUser.setRoles(Collections.emptyList());
        when(userRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(mockUser);
        UserDetails user = userDetailsService.loadUserByUsername("sam");
        assertNotNull(user);
    }
}
