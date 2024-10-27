package com.example.demo2.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
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
