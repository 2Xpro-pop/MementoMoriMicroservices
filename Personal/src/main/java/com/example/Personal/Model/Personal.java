package com.example.Personal.Model;

public class Personal {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private String email;
    private int branchOfficeId;
    private String post;
    private int salaryType;
    private int salaryPercent;
    private double salaryAmount;

    public Personal(int id, String name, String surname, String phoneNumber, String address, String email, int branchOfficeId, String post, int salaryType, int salaryPercent, double salatyAmount) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.branchOfficeId = branchOfficeId;
        this.post = post;
        this.salaryType = salaryType;
        this.salaryPercent = salaryPercent;
        this.salaryAmount = salatyAmount;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBranchOfficeId() {
        return branchOfficeId;
    }

    public void setBranchOfficeId(int branchOfficeId) {
        this.branchOfficeId = branchOfficeId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(int salaryType) {
        this.salaryType = salaryType;
    }

    public int getSalaryPercent() {
        return salaryPercent;
    }

    public void setSalaryPercent(int salaryPercent) {
        this.salaryPercent = salaryPercent;
    }

    public double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(double salatyAmount) {
        this.salaryAmount = salatyAmount;
    }
}
