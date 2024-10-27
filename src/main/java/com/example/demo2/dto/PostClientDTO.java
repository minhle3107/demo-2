package com.example.demo2.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostClientDTO {
    private Long id;
    private String content;
    private Timestamp created_at;
    private String formattedCreatedAt;
    private AuthorDTO authorInfo;
}
