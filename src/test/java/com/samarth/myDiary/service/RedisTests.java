package com.samarth.myDiary.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    void testRedis(){
        redisTemplate.opsForValue().set("email","abc@gmail.com");
        Assertions.assertEquals(redisTemplate.opsForValue().get("email"),"abc@gmail.com");
    }

}
