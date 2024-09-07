package com.samarth.myDiary.service;

import com.samarth.myDiary.entity.DiaryEntry;
import com.samarth.myDiary.repository.DiaryEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class DiaryEntryService {

    @Autowired
    private DiaryEntryRepo diaryEntryRepo;

    public void saveEntry(DiaryEntry diaryEntry){
        diaryEntry.setDate(LocalDateTime.now());
        diaryEntryRepo.save(diaryEntry);
    }
    public List<DiaryEntry> getAll(){
        return diaryEntryRepo.findAll();
    }
    public Optional<DiaryEntry> findById(ObjectId id){
        return diaryEntryRepo.findById(id);
    }
    public void deleteById(ObjectId id){
        diaryEntryRepo.deleteById(id);
    }

}
