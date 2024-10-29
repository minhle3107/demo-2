package com.example.demo2.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationPostDetailsDTO {
    private List<PostDetailsDTO> posts;
    private MetadataPostDTO metadata;
}
