package com.example.submitservice.service;

import com.example.submitservice.dto.CriteriaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "CRITERIA-SERVICE" , url = "http://localhost:8085")
public interface CriteriaService {
    @GetMapping("/criteria")
    public List<CriteriaDto> getAllCriteria();
}
