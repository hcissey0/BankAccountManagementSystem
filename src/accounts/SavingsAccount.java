package accounts;

import customers.Customer;

/**
 * The type Savings account.
 */
public class SavingsAccount extends Account {

    private final double interestRate;
    private final double minimumBalance;

    /**
     * Instantiates a new Savings account.
     *
     * @param customer       the customer
     * @param initialDeposit the initial deposit
     */
    public SavingsAccount(Customer customer, double initialDeposit) {
        super(customer);
        this.deposit(initialDeposit);
        this.interestRate = 3.5;
        this.minimumBalance = 500.0;
    }

    /**
     * Gets interest rate.
     *
     * @return the interest rate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Gets minimum balance.
     *
     * @return the minimum balance
     */
    public double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * Calculate interest double.
     *
     * @return the double
     */
    public double calculateInterest() {
        return this.getBalance() * this.interestRate;
    }

    @java.lang.Override
    public double withdraw(double amount) {

        if (this.getBalance() - amount >= this.minimumBalance) {
            return super.withdraw(amount);
        }
        return 0;
    }

    @java.lang.Override
    public void displayAccountDetails() {
        System.out.println("+-----------------+");
        System.out.println("| Account Details |");
        System.out.println("+-----------------+");
        System.out.println("Account Number: " + this.getAccountNumber());
        System.out.println("Customer: " + this.getCustomer().getName());
        System.out.println("Account Type: " + this.getAccountType());
        System.out.println("Current Balance: " + this.getBalance());
        System.out.println("Interest Rate: " + this.interestRate);
        System.out.println("Minimum Balance: " + this.minimumBalance);
        System.out.println("Interest: " + this.calculateInterest());
        System.out.println("+--------------------------+");

    }

    @java.lang.Override
    public String getAccountType() {
        return "Savings";
    }
}
