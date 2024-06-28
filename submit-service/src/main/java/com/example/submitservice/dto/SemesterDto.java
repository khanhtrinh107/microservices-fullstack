package com.example.submitservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDto {
   private int id;
    private String name;
    private Date startDate;
    private Date endDate;
}
