package com.example.auth.viewmodels;

import lombok.*;

@Setter
@Getter
public class JwtRequest {

    private String login;
    private String password;

}