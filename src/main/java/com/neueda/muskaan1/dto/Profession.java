package com.neueda.muskaan1.dto;

public class Profession {
    private String Job;

    public double getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(double total_amt) {
        this.total_amt = total_amt;
    }

    public Profession(double total_amt) {
        this.total_amt = total_amt;
    }

    private double total_amt;

    public Profession(String job) {
        Job = job;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

}
