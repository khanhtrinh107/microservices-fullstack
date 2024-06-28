package com.example.taskservice.service;

import com.example.taskservice.dto.SubmitDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface TaskService {
    ResponseEntity<?> submit(String jwt, SubmitDto submitDto);
    ResponseEntity<?> approveSubmit(int submitId, int reviewerId);

    ResponseEntity<?> rejectSubmit(int submitId, int reviewerId);
}
