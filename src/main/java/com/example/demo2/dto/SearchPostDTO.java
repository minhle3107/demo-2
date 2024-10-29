package com.example.demo2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchPostDTO {
    private int pageSize;
    private int pageNumber;
}
