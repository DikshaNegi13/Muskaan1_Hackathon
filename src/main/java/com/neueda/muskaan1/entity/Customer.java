package com.neueda.muskaan1.entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.TemporalType;


@Document("customer")
public class Customer {
    public Customer(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Id
    ObjectId id;

    @Field("customer_id")
    private int customerId;
    @NotBlank(message = "Enter First Name")
    @Pattern(regexp = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$", message = "Invalid first name")
    @Field("first")
    @Schema(example = "Andy")
    private String firstName;

    @NotBlank(message = "Enter Last Name")
    @Pattern(regexp = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$", message = "Invalid last name")
    @Field("last")
    @Schema(example = "Paul")
    private String lastName;

    @NotBlank(message = "Enter Gender")
    @Pattern(regexp = "^(M|F)$", message = "Specify 'M' for Male and 'F' for Female")
    @Schema(example = "M")
    private String gender;

    @NotBlank(message = "Enter Job")
    @Pattern(regexp = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$", message   = "Invalid job status")
    @Schema(example = "Lawyer")
    private String job;

    @NotBlank(message = "Date of birth is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth should be in yyyy-MM-dd format")
    @Schema(example = "1997-05-02")
    private String dob;

    public Customer() {
        // Empty no-arg constructor.
    }

    public Customer(int customerId, String firstName, String lastName, String gender,
                    String job, String  dob) {


        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int  customerId) {
        this.customerId = customerId;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                ", dob=" + dob +
                '}';
    }
}