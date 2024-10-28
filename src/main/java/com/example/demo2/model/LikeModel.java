package com.example.demo2.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeModel {
    private Long id;
    private Long employee_id;
    private Long post_id;
}
