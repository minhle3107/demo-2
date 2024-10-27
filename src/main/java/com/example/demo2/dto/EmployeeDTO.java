package com.example.demo2.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String avatar;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
}
