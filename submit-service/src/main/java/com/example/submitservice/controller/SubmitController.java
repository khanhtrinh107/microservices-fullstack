package com.example.submitservice.controller;

import com.example.submitservice.dto.UserDto;
import com.example.submitservice.model.Submit;
import com.example.submitservice.service.SubmitService;
import com.example.submitservice.service.UserService;
import feign.Response;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Builder
@RequestMapping("/submit")
public class SubmitController {
    private final UserService userService;
    private final SubmitService submitService;
    @GetMapping
    public ResponseEntity<?> getAllSubmit(){
        return new ResponseEntity<>(submitService.getAllSubmit() , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSubmit(@RequestHeader("Authorization") String jwt, @RequestBody Submit submit) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        if(user == null){
            throw new Exception("Invalid Token");
        }
        return new ResponseEntity<>(submitService.createSubmit(submit) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubmit(@RequestHeader("Authorization") String jwt, @RequestBody Submit submit , @PathVariable(name = "id") int id) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        if(user == null){
            throw new Exception("Invalid Token");
        }
        if(user.getRole().equals("ROLE_USER")){
            throw new Exception("Invalid role");
        }
        return new ResponseEntity<>(submitService.updateSubmit(submit,id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubmitById(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(submitService.getSubmitById(id) , HttpStatus.OK);
    }

    @GetMapping("/submitter/{id}")
    public ResponseEntity<?> getSubmitBySubmitterId(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(submitService.getSubmitBySubmitterId(id) , HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> getSubmitByStatus(@RequestParam(name = "status") String status){
        return new ResponseEntity<>(submitService.getSubmitByStatus(status), HttpStatus.OK);
    }

    @PutMapping("/approve/{submitId}/{reviewerId}")
    public ResponseEntity<?> approveSubmit(@PathVariable(name = "submitId") int submitId, @PathVariable(name = "reviewerId") int reviewerId){
        return new ResponseEntity<>(submitService.approveSubmit(submitId, reviewerId), HttpStatus.OK);
    }

    @PutMapping("/reject/{submitId}/{reviewerId}")
    public ResponseEntity<?> rejectSubmit(@PathVariable(name = "submitId") int submitId, @PathVariable(name = "reviewerId") int reviewerId){
        return new ResponseEntity<>(submitService.rejectSubmit(submitId, reviewerId), HttpStatus.OK);
    }
}
