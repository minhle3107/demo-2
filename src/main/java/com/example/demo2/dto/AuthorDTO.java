package com.example.demo2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {
    private Long authorId;
    private String authorName;
    private String avatar;
}
