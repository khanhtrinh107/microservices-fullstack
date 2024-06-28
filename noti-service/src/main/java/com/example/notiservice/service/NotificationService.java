package com.example.notiservice.service;

import com.example.notiservice.entity.Notification;

import java.util.List;

public interface NotificationService {
    public Notification createNotification(Notification notification);
    public Notification updateNotification(Notification notification, int id);
    public Notification findNotificationById(int id);
    public List<Notification> getNotificationBySendToUserId(int sendToUserId);
}
