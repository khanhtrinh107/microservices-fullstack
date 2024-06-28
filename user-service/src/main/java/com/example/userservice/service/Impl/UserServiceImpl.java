package com.example.userservice.service.Impl;

import com.example.userservice.jwt.JwtProvider;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User getUserProfile(String jwt) {
        String username = JwtProvider.getUsernameForJwtToken(jwt);
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow();
    }
}
