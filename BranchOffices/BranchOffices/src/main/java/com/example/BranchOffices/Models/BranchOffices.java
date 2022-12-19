package com.example.BranchOffices.Models;

public class BranchOffices {
    private int id;
    private String name;
    private String address;
    private double budget;

    public BranchOffices(int id,String name, String address, double budget) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
