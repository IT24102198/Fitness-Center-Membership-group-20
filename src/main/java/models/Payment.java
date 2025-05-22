package models;

public class Payment {
    private String name;
    private String memberId;
    private double amount;
    private String mode;
    private String date;

    //Default Constructor ( create a Payment object without setting any values)
    public Payment() {}

    // Parameterized constructor
    public Payment(String name, String memberId, double amount, String mode, String date) {
        this.name = name;
        this.memberId = memberId;
        this.amount = amount;
        this.mode = mode;
        this.date = date;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Updated to use getters for encapsulation
    public String toFileString() {
        return getName() + "," + getMemberId() + "," + getAmount() + "," + getMode() + "," + getDate() + "\n";
    }
}
