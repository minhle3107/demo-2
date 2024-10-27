package com.example.demo2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentClientDTO {
    private AccountDTO account;
    private CommentDetailsClientDTO commentDetails;
}
