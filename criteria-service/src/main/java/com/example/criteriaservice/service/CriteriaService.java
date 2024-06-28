package com.example.criteriaservice.service;

import com.example.criteriaservice.entity.Criteria;

import java.util.List;

public interface CriteriaService {
    List<Criteria> getAllCriteria();
    Criteria getCriteriaById(int id);
    Criteria createCriteria(Criteria criteria);
    Criteria updateCriteria(Criteria criteria , int id);

    List<Criteria> getCriteriaBySemesterId(int id);
}
