package com.example.notiservice.service.impl;

import com.example.notiservice.entity.Notification;
import com.example.notiservice.repository.NotificationRepository;
import com.example.notiservice.service.NotificationService;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification, int id) {
        Notification currentNotification = notificationRepository.findById(id).orElseThrow();
        currentNotification.setIsRead(notification.getIsRead());
        return notificationRepository.save(currentNotification);
    }

    @Override
    public Notification findNotificationById(int id) {
        return notificationRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Notification> getNotificationBySendToUserId(int sendToUserId) {
        return notificationRepository.getNotificationBySendToUserId(sendToUserId);
    }
}
