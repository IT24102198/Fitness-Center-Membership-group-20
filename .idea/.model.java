package org.example.model;

import java.util.Date;
import java.util.UUID;

public class Payment {

    private String id;

    private int customerId;

    private String customerName;

    private double amount;

    private int membershipYears;

    private Date paymentDate;

    public Payment() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getMembershipYears() {
        return membershipYears;
    }

    public void setMembershipYears(int membershipYears) {
        this.membershipYears = membershipYears;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}

