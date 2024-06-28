package com.example.submitservice.service.impl;

import com.example.submitservice.dto.CriteriaDto;
import com.example.submitservice.dto.SemesterDto;
import com.example.submitservice.dto.SubmitDto;
import com.example.submitservice.model.Submit;
import com.example.submitservice.repository.SubmitRepository;
import com.example.submitservice.service.CriteriaService;
import com.example.submitservice.service.SemesterService;
import com.example.submitservice.service.SubmitService;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Builder
public class SubmitServiceImpl implements SubmitService {

    private final SubmitRepository submitRepository;
    private final SemesterService semesterService;
    private final CriteriaService criteriaService;

    @Override
    public Submit createSubmit(Submit submit) {
        submit.setStatus("WAITING");
        return submitRepository.save(submit);
    }

    @Override
    public Submit updateSubmit(Submit submit, int submitId) {
        Submit currentSubmit = submitRepository.findById(submitId).orElseThrow();
        if(submit.getStatus() != null){
            currentSubmit.setStatus(submit.getStatus());
        }
        if(submit.getEvidentFile() != null){
            currentSubmit.setEvidentFile(submit.getEvidentFile());
        }
        if(!ObjectUtils.isEmpty(submit.getSemesterId())){
            currentSubmit.setSemesterId(submit.getSemesterId());
        }
        return submitRepository.save(currentSubmit);
    }

    @Override
    public Submit deleteSubmit(int submitId) {
        Submit currentSubmit = submitRepository.findById(submitId).orElseThrow();
        submitRepository.deleteById(submitId);
        return currentSubmit;
    }

    @Override
    public Submit getSubmitById(int submitId) {
        Submit currentSubmit = submitRepository.findById(submitId).orElseThrow();
        return currentSubmit;
    }

    @Override
    public List<SubmitDto> getAllSubmit() {
        List<Submit> submits = submitRepository.findAll();
        List<SemesterDto> semesterDtos = semesterService.getAllSemester();
        List<CriteriaDto> criteriaDtos = criteriaService.getAllCriteria();
        List<SubmitDto> res = new ArrayList<>();
        Map<Integer, SemesterDto> semesterDtoMap = new HashMap<>();
        for(SemesterDto semesterDto : semesterDtos){
            semesterDtoMap.put(semesterDto.getId() , semesterDto);
        }
        Map<Integer , CriteriaDto> criteriaDtoMap = new HashMap<>();
        for(CriteriaDto criteriaDto : criteriaDtos){
            criteriaDtoMap.put(criteriaDto.getId() , criteriaDto);
        }
        for(Submit submit : submits){
            SubmitDto submitDto = new SubmitDto();
            submitDto.setSubmit(submit);
            submitDto.setSemester(semesterDtoMap.get(submit.getSemesterId()));
            submitDto.setCriteria(criteriaDtoMap.get(submit.getCriteriaId()));
            res.add(submitDto);
        }
        return res;
    }

    @Override
    public List<SubmitDto> getSubmitBySubmitterId(int submitterId) {
        List<Submit> submits = submitRepository.findAll();
        List<SemesterDto> semesterDtos = semesterService.getAllSemester();
        List<CriteriaDto> criteriaDtos = criteriaService.getAllCriteria();
        List<SubmitDto> res = new ArrayList<>();
        Map<Integer, SemesterDto> semesterDtoMap = new HashMap<>();
        for(SemesterDto semesterDto : semesterDtos){
            semesterDtoMap.put(semesterDto.getId() , semesterDto);
        }
        Map<Integer , CriteriaDto> criteriaDtoMap = new HashMap<>();
        for(CriteriaDto criteriaDto : criteriaDtos){
            criteriaDtoMap.put(criteriaDto.getId() , criteriaDto);
        }
        for(Submit submit : submits){
            SubmitDto submitDto = new SubmitDto();
            submitDto.setSubmit(submit);
            submitDto.setSemester(semesterDtoMap.get(submit.getSemesterId()));
            submitDto.setCriteria(criteriaDtoMap.get(submit.getCriteriaId()));
            res.add(submitDto);
        }
        return res.stream().filter(s -> s.getSubmit().getSubmitterId() == submitterId).collect(Collectors.toList());
    }

    @Override
    public List<SubmitDto> getSubmitByStatus(String status) {
        List<Submit> submits = submitRepository.getSubmitByStatus(status);
        List<SemesterDto> semesterDtos = semesterService.getAllSemester();
        List<CriteriaDto> criteriaDtos = criteriaService.getAllCriteria();
        List<SubmitDto> res = new ArrayList<>();
        Map<Integer, SemesterDto> semesterDtoMap = new HashMap<>();
        for(SemesterDto semesterDto : semesterDtos){
            semesterDtoMap.put(semesterDto.getId() , semesterDto);
        }
        Map<Integer , CriteriaDto> criteriaDtoMap = new HashMap<>();
        for(CriteriaDto criteriaDto : criteriaDtos){
            criteriaDtoMap.put(criteriaDto.getId() , criteriaDto);
        }
        for(Submit submit : submits){
            SubmitDto submitDto = new SubmitDto();
            submitDto.setSubmit(submit);
            submitDto.setSemester(semesterDtoMap.get(submit.getSemesterId()));
            submitDto.setCriteria(criteriaDtoMap.get(submit.getCriteriaId()));
            res.add(submitDto);
        }
        return res;
    }

    @Override
    public Submit approveSubmit(int submitId, int reviewerId) {
        Submit submit = submitRepository.findById(submitId).orElseThrow();
        submit.setReviewerId(reviewerId);
        submit.setStatus("APPROVE");
        return submitRepository.save(submit);
    }

    @Override
    public Submit rejectSubmit(int submitId, int reviewerId) {
        Submit submit = submitRepository.findById(submitId).orElseThrow();
        submit.setReviewerId(reviewerId);
        submit.setStatus("REJECTED");
        return submitRepository.save(submit);
    }

}
