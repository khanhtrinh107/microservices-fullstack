package com.example.criteriaservice.controller;

import com.example.criteriaservice.entity.Criteria;
import com.example.criteriaservice.service.CriteriaService;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/criteria")
@Builder
public class CriteriaController {
    private final CriteriaService criteriaService;
    @GetMapping
    public ResponseEntity<?> getAllCriteria(){
        return new ResponseEntity<>(criteriaService.getAllCriteria(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCriteria(@RequestBody Criteria criteria){
        return new ResponseEntity<>(criteriaService.createCriteria(criteria), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCriteriaById(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(criteriaService.getCriteriaById(id) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCriteriaById(@PathVariable(name = "id") int id,  @RequestBody Criteria criteria){
        return new ResponseEntity<>(criteriaService.updateCriteria(criteria, id) , HttpStatus.OK);
    }

    @GetMapping("/semester/{id}")
    public ResponseEntity<?> getCriteriaBySemesterId(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(criteriaService.getCriteriaBySemesterId(id), HttpStatus.OK);
    }
}
