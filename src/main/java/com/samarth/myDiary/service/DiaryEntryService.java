package com.samarth.myDiary.service;

import com.samarth.myDiary.entity.DiaryEntry;
import com.samarth.myDiary.entity.User;
import com.samarth.myDiary.repository.DiaryEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class DiaryEntryService {

    @Autowired
    private DiaryEntryRepo diaryEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(DiaryEntry diaryEntry, String username){
        try {
            User myUser = userService.findByUsername(username);
            diaryEntry.setDate(LocalDateTime.now());
            DiaryEntry saved = diaryEntryRepo.save(diaryEntry);
            myUser.getDiaryEntries().add(saved);
            userService.saveEntry(myUser);
        }catch (Exception e){
            throw new RuntimeException("An error occurred while saving entry:",e);
        }
    }

    public void saveEntry(DiaryEntry diaryEntry){
        diaryEntryRepo.save(diaryEntry);
    }

    public List<DiaryEntry> getAll(){
        return diaryEntryRepo.findAll();
    }
    public Optional<DiaryEntry> findById(ObjectId id){
        return diaryEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id, String username){
        User myUser = userService.findByUsername(username);
        myUser.getDiaryEntries().removeIf((x -> x.getId().equals(id)));
        userService.saveEntry(myUser);
        diaryEntryRepo.deleteById(id);
    }

}
