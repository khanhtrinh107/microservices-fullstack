package com.example.submitservice.dto;

import com.example.submitservice.model.Submit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitDto {
    private Submit submit;
    private SemesterDto semester;
    private CriteriaDto criteria;
}
