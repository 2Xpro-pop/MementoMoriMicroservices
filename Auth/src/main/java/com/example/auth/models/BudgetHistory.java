package com.example.auth.models;

import lombok.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetHistory {
    private int id;
    private double action;
    private String description;
    private int branchOfficeId;
}
