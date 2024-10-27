package com.example.demo2.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostWithComment {

    private PostClientDTO post;
    private List<CommentClientDTO> comments;

}
