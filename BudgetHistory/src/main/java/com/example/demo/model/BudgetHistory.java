package com.example.demo.model;

public class BudgetHistory {
    private int id;
    private double action;
    private String description;
    private int BranchOfficeId;

    public BudgetHistory(int id, double action, String description, int branchOfficeId) {
        this.id = id;
        this.action = action;
        this.description = description;
        BranchOfficeId = branchOfficeId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAction() {
        return action;
    }

    public void setAction(double action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBranchOfficeId() {
        return BranchOfficeId;
    }

    public void setBranchOfficeId(int branchOfficeId) {
        BranchOfficeId = branchOfficeId;
    }
}
