package com.example.semestersservice.repository;

import com.example.semestersservice.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester,Integer> {
}
