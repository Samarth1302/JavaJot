package com.samarth.myDiary.scheduler;

import com.samarth.myDiary.cache.AppCache;
import com.samarth.myDiary.entity.DiaryEntry;
import com.samarth.myDiary.entity.User;
import com.samarth.myDiary.enums.Sentiment;
import com.samarth.myDiary.repository.UserRepoImpl;
import com.samarth.myDiary.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepo;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 8 * * SUN")
    public void fetchUsersAndSA(){
        List<User> users= userRepo.getUserForSA();
        for(User user:users){
            List<DiaryEntry> diaryEntries = user.getDiaryEntries();
            List<Sentiment> sentiments = diaryEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(7))).map(x->x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCounts = new HashMap<>();
            for(Sentiment sentiment:sentiments){
                if(sentiment!=null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment maxSentiment = null;
            int maxCount=0;
            for(Map.Entry<Sentiment,Integer> entry: sentimentCounts.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount= entry.getValue();
                    maxSentiment = entry.getKey();
                }
            }
            if(maxSentiment!=null) {
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", maxSentiment.toString());
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void refreshCache(){
        appCache.init();
    }

}
