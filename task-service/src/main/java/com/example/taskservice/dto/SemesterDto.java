package com.example.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SemesterDto {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
}
