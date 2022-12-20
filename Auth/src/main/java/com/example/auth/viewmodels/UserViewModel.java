package com.example.auth.viewmodels;

import com.example.auth.models.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserViewModel {
    private String login;
    private String password;
    private String role;
    private int branchOfficeId;
}
