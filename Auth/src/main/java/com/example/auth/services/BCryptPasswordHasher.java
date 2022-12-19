package com.example.auth.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordHasher implements PasswordHasher{
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);

    @Override
    public String hash(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean check(String hash, String rawPassword) {
        return passwordEncoder.matches(rawPassword, hash);
    }
}
