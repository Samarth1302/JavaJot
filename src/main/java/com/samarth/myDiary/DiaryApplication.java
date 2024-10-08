package com.samarth.myDiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class DiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiaryApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager txn(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
	//PlatformTransaction manager
	//Mongo transaction manager
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
