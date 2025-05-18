package org.example.model;

import java.util.Date;
import java.util.UUID;

public class RenewQueue {

    private String id;

    private int CustomerId;

    private String customerName;

    private String queuedDate;

    private Date renewalDate;

    public RenewQueue() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getQueuedDate() {
        return queuedDate;
    }

    public void setQueuedDate(String queuedDate) {
        this.queuedDate = queuedDate;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }
}
