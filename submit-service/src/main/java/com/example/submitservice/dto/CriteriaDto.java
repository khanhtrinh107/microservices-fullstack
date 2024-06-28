package com.example.submitservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CriteriaDto {
    private int id;
    private String name;
    private String description;
    private int score;
    private int semesterId;
}
