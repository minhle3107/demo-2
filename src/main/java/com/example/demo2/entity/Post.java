package com.example.demo2.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long id;
    private String title;
    private String content;
    private Long author_id;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
}
