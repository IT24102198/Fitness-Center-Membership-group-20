
package models;

public class Payment {
    private String name;
    private String memberId;
    private double amount;
    private String mode;
    private String date;

    public Payment(String name, String memberId, double amount, String mode, String date) {
        this.name = name;
        this.memberId = memberId;
        this.amount = amount;
        this.mode = mode;
        this.date = date;
    }

    public String toFileString() {
        return name + "," + memberId + "," + amount + "," + mode + "," + date + "\n";
    }

    // Getters (optional for further expansion)
}
