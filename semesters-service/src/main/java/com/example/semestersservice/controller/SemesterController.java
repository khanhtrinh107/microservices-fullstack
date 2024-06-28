package com.example.semestersservice.controller;

import com.example.semestersservice.entity.Semester;
import com.example.semestersservice.service.SemesterService;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Builder
@RequestMapping("/semester")
public class SemesterController {
    private final SemesterService semesterService;

    @GetMapping
    public ResponseEntity<?> getAllSemesters(){
        return new ResponseEntity<>(semesterService.getAllSemesters() , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSemester(@RequestBody Semester semester){
        return new ResponseEntity<>(semesterService.createSemester(semester) , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSemesterById(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(semesterService.getSemesterById(id) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSemester(@PathVariable(name = "id") int id , @RequestBody Semester semester){
        return new ResponseEntity<>(semesterService.updateSemester(semester,id) , HttpStatus.OK);
    }
}
