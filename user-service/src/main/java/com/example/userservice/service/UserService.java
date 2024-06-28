package com.example.userservice.service;

import com.example.userservice.model.User;

public interface UserService {
    public User getUserProfile(String jwt);
    public User getUserById(int id);
}
