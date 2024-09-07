package com.samarth.myDiary.controller;

import com.samarth.myDiary.entity.DiaryEntry;
import com.samarth.myDiary.service.DiaryEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/diary")
public class DiaryController_v2 {

    @Autowired
    private DiaryEntryService diaryEntryService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<DiaryEntry> all = diaryEntryService.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody DiaryEntry myEntry){
        try{
            diaryEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getDiaryById(@PathVariable ObjectId myId) {
        Optional<DiaryEntry> diaryEntry = diaryEntryService.findById(myId); //box like wrapper
        if(diaryEntry.isPresent()){
            return new ResponseEntity<>(diaryEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateDiaryById(@PathVariable ObjectId myId,@RequestBody DiaryEntry newEntry) {
        DiaryEntry old = diaryEntryService.findById(myId).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
            diaryEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteDiaryById(@PathVariable ObjectId myId) {
        diaryEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } //? = wild card like any in typescript
}