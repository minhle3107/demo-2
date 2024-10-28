package com.example.demo2.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like {
    private Long id;
    private Long employee_id;
    private Long post_id;
}
