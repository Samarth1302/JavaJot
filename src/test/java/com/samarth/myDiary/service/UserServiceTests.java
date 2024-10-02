package com.samarth.myDiary.service;

import com.samarth.myDiary.entity.User;
import com.samarth.myDiary.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;

    @Disabled
    @Test
    public void testFindByUsername(){
        User user =userRepo.findByUsername("sam");
        assertTrue(!user.getDiaryEntries().isEmpty());
    }


}
