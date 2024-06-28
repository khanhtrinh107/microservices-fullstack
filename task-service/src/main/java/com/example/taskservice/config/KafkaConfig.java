package com.example.taskservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic createNotificationTopic(){
        return new NewTopic("notification-topic" , 3 , (short) 1);
    }
    @Bean
    public NewTopic createNotificationTopic2(){
        return new NewTopic("notification-topic2" , 3 , (short) 1);
    }
}
