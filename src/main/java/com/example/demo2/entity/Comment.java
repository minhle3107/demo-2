package com.example.demo2.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Long id;
    private Long employee_id;
    private Long post_id;
    private Long parent_id;
    private String content;
    private String image_url;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
}
