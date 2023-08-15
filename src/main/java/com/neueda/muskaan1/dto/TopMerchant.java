package com.neueda.muskaan1.dto;

public class TopMerchant {
    private String merchant;
    private double totalSpending;
    private String city;
    private String state;
    private int cityPopulation;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(int cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public TopMerchant(String merchant, double totalSpending, String city, String state, int cityPopulation) {
        this.merchant = merchant;
        this.totalSpending = totalSpending;
        this.city = city;
        this.state = state;
        this.cityPopulation = cityPopulation;
    }

}
