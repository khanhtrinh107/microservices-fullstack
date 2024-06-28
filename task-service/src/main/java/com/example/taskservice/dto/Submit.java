package com.example.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submit {
    private int semesterId;
    private int criteriaId;
    private int submitterId;
    private MultipartFile evidentFile;
    private String description;
}
