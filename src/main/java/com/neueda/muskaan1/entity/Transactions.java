package com.neueda.muskaan1.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("transaction")

public class Transactions {
    @Id
    private ObjectId id;
    @Field("customer_id")
    public int customerId;

    @Field("trans_date_trans_time")
    public String transDateTransTime;

    //@NotBlank(message = "Amount is required")
    @Pattern(regexp = "\\(?\\d+\\.\\d+\\)?", message = "Invalid Amount entered")
    public double amt;
    @Field("trans_num")
    @NotBlank(message = "Transaction number is required")
    @Pattern(regexp = "^0*?[1-9]\\d*$", message = "Invalid Transaction number entered")
    public int transactionNum;

    @NotBlank(message = "Enter city")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid city name")
    public String city;
    @NotBlank(message = "Enter state")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid state name")
    public String state;
    @Field("city_population")
    public int cityPopulation;
    public String merchant;
    @NotBlank(message = "Enter category")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid category name")
    public String category;
    public String first;
    public String last;
    public String dob;
    @NotBlank(message = "Enter Gender")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be 'Male' or 'Female'")
    public String gender;
    @Field("Job")
    @NotBlank(message = "Enter Job")
    @Pattern(regexp = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$", message   = "Invalid job status")
    public String job;

    public String getTransDateTransTime() {
        return transDateTransTime;
    }

    public void setTransDateTransTime(String transDateTransTime) {
        this.transDateTransTime = transDateTransTime;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public int getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(int trans_num) {
        this.transactionNum = transactionNum;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customer_id) {
        this.customerId = customerId;
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

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Transactions(String transDateTransTime, double amt, int transactionNum, int customerId, String city, String state, int cityPopulation, String merchant, String category, String first, String last, String gender, String job, String dob) {

        this.transDateTransTime = transDateTransTime;
        this.amt = amt;
        this.transactionNum = transactionNum;
        this.customerId = customerId;
        this.city = city;
        this.state = state;
        this.cityPopulation = cityPopulation;
        this.merchant = merchant;
        this.category = category;
        this.first = first;
        this.last = last;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                ", transDateTransTime='" + transDateTransTime + '\'' +
                ", amt=" + amt +
                ", transactionNum=" + transactionNum +
                ", customerIdd=" + customerId +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", cityPopulation=" + cityPopulation +
                ", merchant='" + merchant + '\'' +
                ", category='" + category + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
    }


