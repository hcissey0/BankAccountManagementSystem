package accounts;

import customers.Customer;
import transactions.Transactable;

/**
 * The type Account.
 */
public abstract class Account implements Transactable {
    private static int accountCounter = 0;
    private final String accountNumber;
    private Customer customer;
    private double balance;
    private String status;

    /**
     * Instantiates a new Account.
     *
     * @param customer the customer
     */
    Account(Customer customer) {
        this.accountNumber = generateAccountNumber();
        this.balance = 0;
        this.customer = customer;
        this.status = "Active";
    }

    private static String generateAccountNumber() {
        return "ACC" + String.format("%03d", ++accountCounter);
    }

    // getters

    /**
     * Gets account counter.
     *
     * @return the account counter
     */
    public static int getAccountCounter() {
        return accountCounter;
    }

    /**
     * Gets account number.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    // setters

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Display account details.
     */
    public abstract void displayAccountDetails();

    /**
     * Gets account type.
     *
     * @return the account type
     */
    public abstract String getAccountType();

    /**
     * Deposit double.
     *
     * @param amount the amount
     * @return the double
     */
    public double deposit(double amount) {
        this.balance += amount;
        return this.balance;
    }

    /**
     * Withdraw double.
     *
     * @param amount the amount
     * @return the double
     */
    public double withdraw(double amount) {
        this.balance -= amount;
        return this.balance;
    }

    @Override
    public boolean processTransaction(double amount, String type) {
        if (type.equalsIgnoreCase("Deposit")) {
            if (amount <= 0) {
                return false;
            }
            this.deposit(amount);
            return true;
        } else if (type.equalsIgnoreCase("Withdrawal")) {
            if (amount <= 0) {
                return false;
            }
            double oldBalance = this.balance;
            this.withdraw(amount);
            return this.balance != oldBalance;
        }
        return false;
    }
}
