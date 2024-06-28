package com.example.semestersservice.service;

import com.example.semestersservice.entity.Semester;

import java.util.List;

public interface SemesterService {
    List<Semester> getAllSemesters();
    Semester getSemesterById(int id);
    Semester createSemester(Semester semester);
    Semester updateSemester(Semester semester , int id);
}
