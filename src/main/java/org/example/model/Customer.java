package org.example.model;

import java.util.Date;

public class Customer {

    private int registerNo;

    private String name;

    private int age;

    private String address;

    private String email;

    private int mobileNo;

    private int workoutPlanNo;

    private int weight;

    private int height;

    private String status;

    private Date membershipStartDate;

    private Date membershipEndDate;

    public int getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(int registerNo) {
        this.registerNo = registerNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(int mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getWorkoutPlanNo() {
        return workoutPlanNo;
    }

    public void setWorkoutPlanNo(int workoutPlanNo) {
        this.workoutPlanNo = workoutPlanNo;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Date getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(Date membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public Date getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(Date membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
