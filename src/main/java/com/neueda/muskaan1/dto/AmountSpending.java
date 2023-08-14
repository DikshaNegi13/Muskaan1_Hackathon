package com.neueda.muskaan1.dto;

public class AmountSpending {
    private String spendingType; // "Low" or "High"
    private double total_amt;

    public AmountSpending(String spendingType, double total_amt) {
        this.spendingType = spendingType;
        this.total_amt = total_amt;
    }

    public String getSpendingType() {
        return spendingType;
    }

    public void setSpendingType(String spendingType) {
        this.spendingType = spendingType;
    }

    public double getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(double total_amt) {
        this.total_amt = total_amt;
    }
}
