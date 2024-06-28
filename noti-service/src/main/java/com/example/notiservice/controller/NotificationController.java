package com.example.notiservice.controller;

import com.example.notiservice.entity.Notification;
import com.example.notiservice.service.NotificationService;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Builder
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllNotification(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(notificationService.getNotificationBySendToUserId(id) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNotification(@PathVariable(name = "id") int id , @RequestBody Notification notification){
        return new ResponseEntity<>(notificationService.updateNotification(notification , id) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNotification(@RequestBody Notification notification){
        return new ResponseEntity<>(notificationService.createNotification(notification) , HttpStatus.CREATED);
    }
}
