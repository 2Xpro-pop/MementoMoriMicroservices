package com.example.Schedule.Model;

public class Schedule {
    private int id;
    private int patientId;
    private int postId;
    private String date;
    private double price;

    public Schedule(int id, int patientId, int postId, String date, double price) {
        this.id = id;
        this.patientId = patientId;
        this.postId = postId;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
