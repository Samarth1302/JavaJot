package com.samarth.myDiary.repository;

import com.samarth.myDiary.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepoImplTests {

    @Autowired
    private UserRepoImpl userRepo;

    @Test
    public void test(){
        Assertions.assertNotNull(userRepo.getUserForSA());
    }

}
