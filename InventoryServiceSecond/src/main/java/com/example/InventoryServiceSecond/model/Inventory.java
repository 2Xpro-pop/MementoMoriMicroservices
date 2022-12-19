package com.example.InventoryServiceSecond.model;

public class Inventory {

    private int id;
    private String name;
    private int amount;
    private int BranchOfficeId;

    public Inventory(int id, String name, int amount, int BranchOfficeId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.BranchOfficeId = BranchOfficeId;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBranchOfficeId() {
        return BranchOfficeId;
    }

    public void setBranchOfficeId(int branchOfficeId) {
        BranchOfficeId = branchOfficeId;
    }
}
