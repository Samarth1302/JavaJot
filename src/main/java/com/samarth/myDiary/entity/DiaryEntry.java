package com.samarth.myDiary.entity;

import com.samarth.myDiary.enums.Sentiment;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "diary_entries")
@Data //lombok adding getters, setters etc. during compilation no need of explicit
@NoArgsConstructor
public class DiaryEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;
}
