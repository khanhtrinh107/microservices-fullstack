package com.example.notiservice.service;

import com.example.notiservice.dto.NotificationDto;
import com.example.notiservice.entity.Notification;
import com.example.notiservice.repository.NotificationRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
@Builder
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    @KafkaListener(topics = "notification-topic2" , groupId = "notification-group")
    public void consume(NotificationDto notification){
        Notification noti = new Notification();
        noti.setCreatedUserId(notification.getSenderId());
        noti.setContent(notification.getContent());
        noti.setCreatedDate(new Date());
        noti.setIsRead(0);
        if(ObjectUtils.isEmpty(notification.getSendToUserId())){
            noti.setRole("ROLE_ADMIN");
        } else{
            noti.setSendToUserId(notification.getSendToUserId());
        }
        notificationRepository.save(noti);
    }

}
