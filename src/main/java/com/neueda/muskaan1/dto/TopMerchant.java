package com.neueda.muskaan1.dto;

public class TopMerchant {

    private String merchant;
    private double totalSpending;

    public TopMerchant(String merchant, double totalSpending) {
        this.merchant = merchant;
        this.totalSpending = totalSpending;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public double getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }
}
