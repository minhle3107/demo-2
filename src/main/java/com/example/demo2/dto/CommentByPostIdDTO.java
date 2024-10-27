package com.example.demo2.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentByPostIdDTO {

    private Long comment_id;
    private AccountDTO account;
    private CommentDTO commentDetail;
    private Long parent_id;
    private Timestamp created_at;
    private String formattedCreatedAt;
    private List<CommentByPostIdDTO> childComments;


}
