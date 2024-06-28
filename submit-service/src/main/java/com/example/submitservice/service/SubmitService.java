package com.example.submitservice.service;

import com.example.submitservice.dto.SubmitDto;
import com.example.submitservice.model.Submit;

import java.util.List;

public interface SubmitService {
    public Submit createSubmit(Submit submit);
    public Submit updateSubmit(Submit submit, int submitId);
    public Submit deleteSubmit(int submitId);
    public Submit getSubmitById(int submitId);
    public List<SubmitDto> getAllSubmit();
    public List<SubmitDto> getSubmitBySubmitterId(int submitterId);

    public List<SubmitDto> getSubmitByStatus(String status);

    public Submit approveSubmit(int submitId , int reviewerId);
    public Submit rejectSubmit(int submitId , int reviewerId);
}
