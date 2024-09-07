package com.samarth.myDiary.repository;

import com.samarth.myDiary.entity.DiaryEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryEntryRepo extends MongoRepository<DiaryEntry, ObjectId> {

}


//controller (calls)-> service -> repo