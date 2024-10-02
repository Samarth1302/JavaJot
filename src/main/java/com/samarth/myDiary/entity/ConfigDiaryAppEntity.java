package com.samarth.myDiary.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "config_diary_app")
@Data //lombok adding getters, setters etc. during compilation no need of explicit
@NoArgsConstructor
public class ConfigDiaryAppEntity {
    private String key;
    private String value;
}
