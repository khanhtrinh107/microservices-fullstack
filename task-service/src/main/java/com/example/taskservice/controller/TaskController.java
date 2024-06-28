package com.example.taskservice.controller;

import com.example.taskservice.config.CloudinaryService;
import com.example.taskservice.dto.SubmitDto;
import com.example.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/task")

public class TaskController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String testTask(){
        return "oko";
    }

    @PostMapping
    public ResponseEntity<?> submit(@RequestHeader("Authorization") String jwt, @RequestBody SubmitDto submitDto) throws IOException {
        return taskService.submit(jwt, submitDto);
    }

    @PutMapping("/approve/{submitId}/{reviewerId}")
    public ResponseEntity<?> approveSubmit(@PathVariable(name = "submitId") int submitId, @PathVariable(name = "reviewerId") int reviewerId){
        return taskService.approveSubmit(submitId, reviewerId);
    }

    @PutMapping("/reject/{submitId}/{reviewerId}")
    public ResponseEntity<?> rejectSubmit(@PathVariable(name = "submitId") int submitId, @PathVariable(name = "reviewerId") int reviewerId){
        return  taskService.rejectSubmit(submitId, reviewerId);
    }
}
