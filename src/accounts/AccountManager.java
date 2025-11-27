package accounts;

import java.util.Scanner;

/**
 * The type Account manager.
 */
public class AccountManager {

    private final Account[] accounts;
    private int accountCount;

    /**
     * Instantiates a new Account manager.
     */
    public AccountManager() {
        this.accounts = new Account[50];
        this.accountCount = 0;
    }

    /**
     * Gets account count.
     *
     * @return the account count
     */
    public int getAccountCount() {
        return accountCount;
    }

    /**
     * Add account.
     *
     * @param account the account
     */
    public void addAccount(Account account) {
        this.accounts[this.accountCount++] = account;
        System.out.println("Accounts: ");
    }

    /**
     * Find account account.
     *
     * @param accountNumber the account number
     * @return the account
     */
    public Account findAccount(String accountNumber) {
        for (int i = 0; i < this.accountCount; i++) {
            if (this.accounts[i].getAccountNumber().equals(accountNumber)) {
                return this.accounts[i];
            }
        }
        return null;
    }

    /**
     * View all accounts.
     *
     * @param scanner the scanner
     */
    public void viewAllAccounts(Scanner scanner) {
        String[] headers = {
                "ACCOUNT NUMBER",
                "CUSTOMER NAME",
                "TYPE",
                "BALANCE",
                "STATUS"
        };

        if (accountCount == 0) {
            System.out.println("+------------------------+");
            System.out.println("| No accounts available. |");
            System.out.println("+------------------------+");
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            return;
        }

        int[] headerWidth = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            headerWidth[i] = headers[i].length();
        }

        String[][] table = new String[accounts.length + 1][headers.length]; // a 2D array to hold table data
        table[0] = headers;

        int rowIndex = 1;
        for (int i = 0; i < this.accountCount; i++) {// set the with of the columes according to the longest data, and add data to the table
            Account acc = this.accounts[i];
            table[rowIndex][0] = acc.getAccountNumber();
            if (headerWidth[0] < acc.getAccountNumber().length()) {
                headerWidth[0] = acc.getAccountNumber().length();
            }

            table[rowIndex][1] =
                    (
                            acc instanceof CheckingAccount ?
                                    acc.getCustomer().getName().concat(" (Overdraft Limit: $" + ((CheckingAccount) acc).getOverdraftLimit() + ")") :
                                    acc.getCustomer().getName().concat(" (Interest Rate: " + ((SavingsAccount) acc).getInterestRate() + "%)")
                    );
            if (headerWidth[1] < table[rowIndex][1].length()) {
                headerWidth[1] = table[rowIndex][1].length();
            }

            table[rowIndex][2] =
                    (
                            acc instanceof CheckingAccount ?
                                    acc.getAccountType().concat(" (Monthly Fee: $" + ((CheckingAccount) acc).getMonthlyFee() + ")") :
                                    acc.getAccountType().concat(" (Min Balance: $" + ((SavingsAccount) acc).getMinimumBalance() + ")")
                    );
            if (headerWidth[2] < table[rowIndex][2].length()) {
                headerWidth[2] = table[rowIndex][2].length();
            }
            table[rowIndex][3] = "$" + acc.getBalance();
            if (headerWidth[3] < table[rowIndex][3].length()) {
                headerWidth[3] = table[rowIndex][3].length();
            }
            table[rowIndex][4] = acc.getStatus();
            if (headerWidth[4] < acc.getStatus().length()) {
                headerWidth[4] = acc.getStatus().length();
            }
            rowIndex++;
        }

        for (int i = 0; i < rowIndex; i++) {
            if (i == 0) { // print border line before header
                for (int width : headerWidth) {
                    System.out.print("+");
                    System.out.print("-".repeat(width + 2));
                }
                System.out.println("+");
            }
            for (int j = 0; j < headers.length; j++) {
                System.out.print("| ");
                System.out.printf("%-" + (headerWidth[j] + 1) + "s", table[i][j]); // pad the string with spaces to the right
            }
            System.out.println("|");
            if (i == 0 || i == rowIndex - 1) { // print border line after header and after last row
                for (int width : headerWidth) {
                    System.out.print("+");
                    System.out.print("-".repeat(width + 2));
                }
                System.out.println("+");
            }
        }

        System.out.println();
        System.out.println("Total Accounts: " + (rowIndex - 1));
        System.out.println("Total Bank Balance: %" + getTotalBalance());

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();

    }

    /**
     * Gets total balance.
     *
     * @return the total balance
     */
    public double getTotalBalance() {
        double totalBalance = 0;
        for (int i = 0; i < this.accountCount; i++)
            totalBalance += this.accounts[i].getBalance();

        return totalBalance;
    }

}
