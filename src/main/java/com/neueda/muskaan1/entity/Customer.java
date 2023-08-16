package com.neueda.muskaan1.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



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
    @Pattern(regexp = "^[a-zA-Z\s.'-]*$", message = "Invalid first name")
    @Field("first")
    private String firstName;

    @NotBlank(message = "Enter Last Name")
    @Pattern(regexp = "^[a-zA-Z\s.'-]*$", message = "Invalid last name")
    @Field("last")
    private String lastName;

    @NotBlank(message = "Enter Gender")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be 'Male' or 'Female'")
    private String gender;

    @NotBlank(message = "Enter Job")
    @Pattern(regexp = "^[a-zA-Z\s.'-]*$", message   = "Invalid job status")
    private String job ;

    @NotBlank(message = "Date of birth is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth should be in yyyy-MM-dd format")
//    @Past(message = "Date of birth should be in the past")
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