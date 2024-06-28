package com.example.criteriaservice.service.impl;

import com.example.criteriaservice.entity.Criteria;
import com.example.criteriaservice.repository.CriteriaRepository;
import com.example.criteriaservice.service.CriteriaService;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Builder
public class CriteriaServiceImpl implements CriteriaService {

    private final CriteriaRepository criteriaRepository;
    @Override
    public List<Criteria> getAllCriteria() {
        return criteriaRepository.findAll();
    }

    @Override
    public Criteria getCriteriaById(int id) {
        return criteriaRepository.findById(id).orElseThrow();
    }

    @Override
    public Criteria createCriteria(Criteria criteria) {
        return criteriaRepository.save(criteria);
    }

    @Override
    public Criteria updateCriteria(Criteria criteria, int id) {
        Criteria currentCriteria = criteriaRepository.findById(id).orElseThrow();
        currentCriteria.setDescription(currentCriteria.getDescription());
        currentCriteria.setScore(currentCriteria.getScore());
        currentCriteria.setName(currentCriteria.getName());
        currentCriteria.setSemesterId(currentCriteria.getSemesterId());
        return criteriaRepository.save(currentCriteria);
    }

    @Override
    public List<Criteria> getCriteriaBySemesterId(int id) {
        return criteriaRepository.findBySemesterId(id);
    }
}
