package com.example.submitservice.service;

import com.example.submitservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE" , url = "http://localhost:8081")
public interface UserService {
    @GetMapping("/user/profile")
    public UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable(name = "id") int id);
}

