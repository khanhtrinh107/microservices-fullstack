package com.example.taskservice.service;

import com.example.taskservice.dto.SubmitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "SUBMIT-SERVICE" , url = "http://localhost:8083")
public interface SubmitService {
    @PostMapping("/submit")
    public SubmitDto createSubmit(@RequestHeader("Authorization") String jwt , @RequestBody SubmitDto submitDto);

    @PutMapping("/submit/approve/{submitId}/{reviewerId}")
    public SubmitDto approveSubmit(@PathVariable(name = "submitId") int submitId, @PathVariable(name = "reviewerId") int reviewerId);

    @PutMapping("/submit/reject/{submitId}/{reviewerId}")
    public SubmitDto rejectSubmit(@PathVariable(name = "submitId") int submitId, @PathVariable(name = "reviewerId") int reviewerId);
}
