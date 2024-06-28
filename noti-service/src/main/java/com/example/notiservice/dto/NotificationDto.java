package com.example.notiservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationDto implements Serializable {
    private int senderId;
    private int sendToUserId;
    private String content;
}
