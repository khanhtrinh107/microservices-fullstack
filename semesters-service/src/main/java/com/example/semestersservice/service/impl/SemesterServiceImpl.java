package com.example.semestersservice.service.impl;

import com.example.semestersservice.entity.Semester;
import com.example.semestersservice.repository.SemesterRepository;
import com.example.semestersservice.service.SemesterService;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
@Builder
@Service
public class SemesterServiceImpl implements SemesterService {
    private final SemesterRepository semesterRepository;

    @Override
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    @Override
    public Semester getSemesterById(int id) {
        return semesterRepository.findById(id).orElseThrow();
    }

    @Override
    public Semester createSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    @Override
    public Semester updateSemester(Semester semester, int id) {
        Semester currentSemester = semesterRepository.findById(id).orElseThrow();
        currentSemester.setName(semester.getName());
        currentSemester.setEndDate(semester.getEndDate());
        currentSemester.setStartDate(semester.getStartDate());
        semesterRepository.save(currentSemester);
        return currentSemester;
    }
}
