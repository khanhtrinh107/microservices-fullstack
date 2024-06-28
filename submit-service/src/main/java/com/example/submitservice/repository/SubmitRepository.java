package com.example.submitservice.repository;

import com.example.submitservice.model.Submit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmitRepository extends JpaRepository<Submit,Integer> {
    List<Submit> getSubmitBySubmitterId(int submitterId);
    List<Submit> getSubmitByStatus(String status);
}
