package accounts;

import customers.Customer;

/**
 * The type Checking account.
 */
public class CheckingAccount extends Account {
    private final double overdraftLimit;
    private final double monthlyFee;

    /**
     * Instantiates a new Checking account.
     *
     * @param customer       the customer
     * @param initialDeposit the initial deposit
     */
    public CheckingAccount(Customer customer, double initialDeposit) {
        super(customer);
        this.deposit(initialDeposit);
        this.overdraftLimit = 1000;
        this.monthlyFee = 10;
    }

    /**
     * Gets monthly fee.
     *
     * @return the monthly fee
     */
    public double getMonthlyFee() {
        return monthlyFee;
    }

    /**
     * Gets overdraft limit.
     *
     * @return the overdraft limit
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * Apply monthlyfee.
     */
    public void applyMonthlyfee() {
        if (this.getBalance() > this.monthlyFee) {
            this.setBalance(this.getBalance() - this.monthlyFee);
        }
    }

    @java.lang.Override
    public double withdraw(double amount) {
        if (amount - this.getBalance() < this.overdraftLimit) {
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
        System.out.println("Overdraft: " + this.overdraftLimit);
        System.out.println("Monthly fee: " + this.monthlyFee);
        System.out.println("+--------------------------+");
    }

    @java.lang.Override
    public String getAccountType() {
        return "Checking";
    }
}
