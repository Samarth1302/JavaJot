package com.samarth.myDiary.service;

import com.samarth.myDiary.entity.DiaryEntry;
import com.samarth.myDiary.entity.User;
import com.samarth.myDiary.repository.DiaryEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//    private static final Logger logger = LoggerFactory.getLogger(DiaryEntryService.class);

    @Transactional
    public void saveEntry(DiaryEntry diaryEntry, String username){
        try {
            User myUser = userService.findByUsername(username);
            diaryEntry.setDate(LocalDateTime.now());
            DiaryEntry saved = diaryEntryRepo.save(diaryEntry);
            myUser.getDiaryEntries().add(saved);
            userService.saveUser(myUser);
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

    @Transactional
    public boolean deleteById(ObjectId id, String username){
        boolean removed = false;
        try{
            User myUser = userService.findByUsername(username);
            removed = myUser.getDiaryEntries().removeIf((x -> x.getId().equals(id)));
            if(removed){
                userService.saveUser(myUser);
                diaryEntryRepo.deleteById(id);
            }
        }catch (Exception e){
            throw new RuntimeException("An error occurred while deleting the entry",e);
        }
        return removed;
    }
}
