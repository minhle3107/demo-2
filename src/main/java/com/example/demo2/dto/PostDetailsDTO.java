package com.example.demo2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class PostDetailsDTO extends PostDTO {
    private String author_name;
    private String avatar;
    private String formattedCreatedAt;
}
