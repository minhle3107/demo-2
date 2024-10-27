package com.example.demo2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private Long employee_id;
    private String full_name;
    private String avatar;
}
