package com.example.submitservice.service;

import com.example.submitservice.dto.SemesterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "SEMESTER-SERVICE" , url = "http://localhost:8082")
public interface SemesterService {
    @GetMapping("/semester")
    public List<SemesterDto> getAllSemester();
}
