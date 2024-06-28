package com.example.submitservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Submit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int semesterId;
    private int submitterId;
    private int reviewerId;
    private int criteriaId;
    private String evidentFile;
    private String description;
    private String status;
}
