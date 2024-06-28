package com.example.criteriaservice.repository;

import com.example.criteriaservice.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriteriaRepository extends JpaRepository<Criteria, Integer> {
    List<Criteria> findBySemesterId(int id);
}
