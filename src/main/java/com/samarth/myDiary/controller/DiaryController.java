package com.samarth.myDiary.controller;

import com.samarth.myDiary.entity.DiaryEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    private Map<Long,DiaryEntry> diaryEntries= new HashMap<>();

    @GetMapping
    public List<DiaryEntry> getAll(){
        return new ArrayList<>(diaryEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody DiaryEntry myEntry){
        diaryEntries.put(myEntry.getId(),myEntry);
        return true;
    }
    
    @GetMapping("id/{myId}")
    public DiaryEntry getDiaryById(@PathVariable Long myId) {
        return diaryEntries.get(myId);
    }

    @PutMapping("id/{myId}")
    public DiaryEntry updateDiaryById(@PathVariable Long myId,@RequestBody DiaryEntry myEntry) {
        return diaryEntries.put(myId,myEntry);
    }

    @DeleteMapping("id/{myId}")
    public DiaryEntry deleteDiaryById(@PathVariable Long myId) {
        return diaryEntries.remove(myId);
    }
}
