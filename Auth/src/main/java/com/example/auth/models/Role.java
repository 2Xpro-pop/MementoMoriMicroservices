package com.example.auth.models;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    Admin("ADMIN"),
    Receptionist("RECEPTIONIST");

    private final String vale;

    @Override
    public String getAuthority() {
        return vale;
    }

}