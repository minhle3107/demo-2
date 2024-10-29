package com.example.demo2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetadataPostDTO {
    private int totalItems;
    private int totalPages;
    private int pageSize;
    private int currentPage;
    private int elementsInTheCurrentPage;

}
