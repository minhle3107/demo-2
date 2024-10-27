package com.example.demo2.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListCommentByPostDTO {
    private Long postId;
    private String title;
    private String content;
    private String author_name;
    private String avatar;
    private String created_at;
    private List<CommentByPostIdDTO> comments;
}
