package com.neueda.muskaan1.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("transaction")

public class Transactions {
 @Id
 private ObjectId id;
 @Field("customer_id")
 public String customerId;

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customer_id) {
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
 @Field("trans_date_trans_time")
    public String transDateTransTime;
        public double amt;
        @Field("trans_num")
        public int transactionNum;

        public String city;
        public String state;
        @Field("city_population")
        public int cityPopulation;
        public String merchant;
        public String category;
        public String first;
        public String last;

    public Transactions(String transDateTransTime, double amt, int transactionNum, String customerId, String city, String state, int cityPopulation, String merchant, String category, String first, String last, String gender, String job, String dob) {

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

    public String gender;
        @Field("Job")
        public String job;

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

    public String dob;
    }


