package com.samarth.myDiary.cache;

import com.samarth.myDiary.entity.ConfigDiaryAppEntity;
import com.samarth.myDiary.repository.ConfigDiaryApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API,
    }

    @Autowired
    private ConfigDiaryApp configDiaryApp;

    public Map<String,String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigDiaryAppEntity> all = configDiaryApp.findAll();
        for (ConfigDiaryAppEntity configDiaryAppEntity : all) {
            appCache.put(configDiaryAppEntity.getKey(), configDiaryAppEntity.getValue());
        }
    }
}
