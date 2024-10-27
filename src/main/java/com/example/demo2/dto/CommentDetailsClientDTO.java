package com.example.demo2.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDetailsClientDTO {
    private Long id;
    private Long postId;
    private Long parentId;
    private String content;
    private String imageUrl;
    private String formattedCreatedAt;
    private Timestamp created_at;
    private List<CommentClientDTO> childComments;
}
