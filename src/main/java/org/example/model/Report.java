package org.example.model;

import java.util.UUID;

public class Report {

    private String id;

    private Customer customer;

    private Payment payment;

    public Report() {
    }

    public Report(Customer customer, Payment payment) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
