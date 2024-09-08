package com.samarth.myDiary.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

@Document(collection = "users")
@Data //lombok adding getters, setters etc. during compilation no need of explicit
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull //lombok OP NUll exception checking
    private String password;
    @DBRef //referring diary_entries to users
    private List<DiaryEntry> diaryEntries=new ArrayList<>();
    private List<String> roles;
}
