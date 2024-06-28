package com.example.userservice.controller;

import com.example.userservice.jwt.JwtProvider;
import com.example.userservice.dto.AuthRequest;
import com.example.userservice.dto.AuthResponse;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.Impl.CustomUserServiceImpl;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Builder
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserServiceImpl customUserService;


    @PostMapping("/signup")
    public ResponseEntity<?> createUserHandler(@RequestBody User user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        String role = "ROLE_USER";
        String fullName = user.getFullName();
        String email = user.getEmail();
        String phoneNumber = user.getPhoneNumber();
        String address = user.getAddress();
        if(userRepository.findByUsername(username) != null){
            throw new Exception("Username is already used");
        }
        User createdUser = new User();
        createdUser.setAddress(address);
        createdUser.setUsername(username);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setEmail(email);
        createdUser.setRole(role);
        createdUser.setPhoneNumber(phoneNumber);
        createdUser.setFullName(fullName);
        User savedUser =  userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(JwtProvider.generateToken(authentication));
        authResponse.setMessage("Register Success");
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Success");
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password){
        UserDetails userDetails = customUserService.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
    }

}
