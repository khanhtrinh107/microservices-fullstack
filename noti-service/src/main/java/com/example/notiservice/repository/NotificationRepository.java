package com.example.notiservice.repository;

import com.example.notiservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification , Integer> {
    List<Notification> getNotificationBySendToUserId(int id);
}
