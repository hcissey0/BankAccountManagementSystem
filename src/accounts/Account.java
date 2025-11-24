package accounts;

import customers.Customer;

public abstract class Account {
    private final String accountNumber;
    private Customer customer;
    private double balance;
    private String status;

    private static int accountCounter = 0;

    Account(Customer customer) {
        this.accountNumber = generateAccountNumber();
        this.balance = 0;
        this.customer = customer;
        this.status = "Active";
    }

    private String generateAccountNumber() {
        return "ACC" + String.format("%03d", accountCounter++);
    }

    // getters

    public static int getAccountCounter() {
        return accountCounter;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    // setters

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void displayAccountDetails();
    public abstract String getAccountType();

    public double deposit(double amount) {
        this.balance += amount;
        return this.balance;
    }

    public double withdraw(double amount) {
            this.balance -= amount;
            return this.balance;
    }
}
