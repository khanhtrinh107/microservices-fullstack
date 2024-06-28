package com.example.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitDto {
    private int semesterId;
    private int criteriaId;
    private int submitterId;
    private String evidentFile;
    private String description;
}
