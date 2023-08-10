package com.neueda.muskaan1.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Customer {

   // private long id;
    @Id
    private long customer_id;
    private String firstName;
    private String lastName;
    private String gender;
    private String job;
    private long dob;

    public Customer() {
        // Empty no-arg constructor.
    }

    public Customer(long customer_id, String firstName, String lastName, String gender,
                    String job, long dob) {
        this.customer_id = customer_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public long getDob() {
        return dob;
    }

    public void setDob(long dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                ", dob=" + dob +
                '}';
    }
}
