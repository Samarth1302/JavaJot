package com.samarth.myDiary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass){
        try {
            Object obj = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(obj.toString(), entityClass);
        }catch(Exception e){
            return null;
        }
    }
    public void set(String key, Object obj, Long ttl){
        try {
            ObjectMapper objectMapper =  new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(obj);
            redisTemplate.opsForValue().set(key,jsonValue,ttl*1000,null);
        }catch(Exception e){
            log.error("Exception",e);
        }
    }
}
