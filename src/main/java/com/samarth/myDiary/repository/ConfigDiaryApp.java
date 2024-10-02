package com.samarth.myDiary.repository;

import com.samarth.myDiary.entity.ConfigDiaryAppEntity;
import com.samarth.myDiary.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigDiaryApp extends MongoRepository<ConfigDiaryAppEntity, ObjectId> {

}
