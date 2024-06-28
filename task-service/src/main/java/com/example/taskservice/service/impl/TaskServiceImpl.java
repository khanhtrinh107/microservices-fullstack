package com.example.taskservice.service.impl;

import com.example.taskservice.dto.ErrorMessage;
import com.example.taskservice.dto.NotificationDto;
import com.example.taskservice.dto.SemesterDto;
import com.example.taskservice.dto.SubmitDto;
import com.example.taskservice.service.SemesterService;
import com.example.taskservice.service.SubmitService;
import com.example.taskservice.service.TaskService;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Builder
public class TaskServiceImpl implements TaskService {
    private final SemesterService semesterService;
    private final SubmitService submitService;
    private final KafkaTemplate<String , Object> kafkaTemplate;
    public static boolean isCurrentTimeWithinDates(Date date1, Date date2) {
        Date currentTime = new Date();
        return (currentTime.after(date1) || currentTime.equals(date1)) && currentTime.before(date2);
    }
    @Override
    public ResponseEntity<?> submit(String jwt, SubmitDto submitDto) {
        SemesterDto semester = semesterService.getSemesterById(submitDto.getSemesterId());
        if(isCurrentTimeWithinDates(semester.getStartDate() , semester.getEndDate())){
            return new ResponseEntity<>(new ErrorMessage("Time limit") , HttpStatus.OK);
        }
        SubmitDto submit = submitService.createSubmit(jwt, submitDto);
        NotificationDto notification =  new NotificationDto(submitDto.getSubmitterId(), -1, "Đã nộp phiếu điểm rèn luyện cho bạn");
        kafkaTemplate.send("notification-topic2" , notification);
        return new ResponseEntity<>(submit, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> approveSubmit(int submitId, int reviewerId) {
        SubmitDto submit = submitService.approveSubmit(submitId , reviewerId);
        NotificationDto notification =  new NotificationDto(reviewerId, submit.getSubmitterId(), "Đã phê duyệt phiếu điểm rèn luyện cho bạn");
        kafkaTemplate.send("notification-topic2" , notification);
        return new ResponseEntity<>(submit, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> rejectSubmit(int submitId, int reviewerId) {
        SubmitDto submit = submitService.rejectSubmit(submitId , reviewerId);
        NotificationDto notification =  new NotificationDto(reviewerId, submit.getSubmitterId(), "Đã từ chối phiếu điểm rèn luyện cho bạn");
        kafkaTemplate.send("notification-topic2" , notification);
        return new ResponseEntity<>(submit, HttpStatus.OK);
    }
}
