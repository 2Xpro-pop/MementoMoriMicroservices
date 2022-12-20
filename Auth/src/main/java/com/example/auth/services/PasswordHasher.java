package com.example.auth.services;


public interface PasswordHasher {
    String hash(String password);
    boolean check(String hash, String rawPassword);
}
