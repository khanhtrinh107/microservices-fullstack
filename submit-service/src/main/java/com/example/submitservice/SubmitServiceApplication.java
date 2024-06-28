package com.example.submitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubmitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubmitServiceApplication.class, args);
    }

}
