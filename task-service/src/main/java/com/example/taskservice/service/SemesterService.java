package com.example.taskservice.service;

import com.example.taskservice.dto.SemesterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "SEMESTERS-SERVICE" , url = "http://localhost:8082")
public interface SemesterService {
    @GetMapping("/semester/{id}")
    SemesterDto getSemesterById(@PathVariable(name = "id") int id);
}
