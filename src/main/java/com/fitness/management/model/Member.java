package com.fitness.management.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Member {
    private String firstName;
    private String lastName;
    private String membershipId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastRenewalDate;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getMembershipId() { return membershipId; }
    public void setMembershipId(String membershipId) { this.membershipId = membershipId; }

    public LocalDate getLastRenewalDate() { return lastRenewalDate; }
    public void setLastRenewalDate(LocalDate lastRenewalDate) { this.lastRenewalDate = lastRenewalDate; }
}