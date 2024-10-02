package com.samarth.myDiary.service;

import com.samarth.myDiary.api.response.WeatherResponse;
import com.samarth.myDiary.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private AppCache appCache;

    @Value("${weather_api_key}")
    private String apiKey;
//    private static final String API="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RedisService redisService;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        }else{
            String finalAPI=appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<city>",city).replace("<apiKey>",apiKey);
            ResponseEntity<WeatherResponse> res=restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
            WeatherResponse body=res.getBody();
            if(body!=null){
                redisService.set("weather_of_" + city,body,300l);
            }
            return body;
        }
    }

}
