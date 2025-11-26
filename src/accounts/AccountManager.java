package accounts;

import java.util.*;

/**
 * The type Account manager.
 */
public class AccountManager {

    private final List<Account> accounts;
    private int accountCount;

    /**
     * Instantiates a new Account manager.
     */
    public AccountManager() {
        this.accounts = new ArrayList<Account>(50);
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
        this.accounts.add(account);
        this.accountCount++;
    }

    /**
     * Find account account.
     *
     * @param accountNumber the account number
     * @return the account
     */
    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
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

        Map<String, Integer> headerWidth = new HashMap<>(); // a map of column widths with header names as keys

        String[][] table = new String[accounts.size() + 1][headers.length]; // a 2D array to hold table data

        for (String string : headers) {
            headerWidth.put(string, string.length());
        }


        table[0] = headers;

        int rowIndex = 1;
        for (Account acc : accounts) {// set the with of the columes according to the longest data, and add data to the table

            table[rowIndex][0] = acc.getAccountNumber();
            if (headerWidth.get(headers[0]) < acc.getAccountNumber().length()) {
                headerWidth.replace(headers[0], acc.getAccountNumber().length());
            }

            table[rowIndex][1] =
                    (
                            acc instanceof CheckingAccount ?
                                    acc.getCustomer().getName().concat(" (Overdraft Limit: $" + ((CheckingAccount) acc).getOverdraftLimit() + ")") :
                                    acc.getCustomer().getName().concat(" (Interest Rate: " + ((SavingsAccount) acc).getInterestRate() + "%)")
                    );
//            table[rowIndex][1] = acc.getCustomer().getName();
            if (headerWidth.get(headers[1]) < table[rowIndex][1].length()) {
                headerWidth.replace(headers[1], table[rowIndex][1].length());
            }

            table[rowIndex][2] =
                    (
                            acc instanceof CheckingAccount ?
                                    acc.getAccountType().concat(" (Monthly Fee: $" + ((CheckingAccount) acc).getMonthlyFee() + ")") :
                                    acc.getAccountType().concat(" (Min Balance: $" + ((SavingsAccount) acc).getMinimumBalance() + ")")
                    );
//            table[rowIndex][2] = acc.getAccountType();
            if (headerWidth.get(headers[2]) < table[rowIndex][2].length()) {
                headerWidth.replace(headers[2], table[rowIndex][2].length());
            }
            table[rowIndex][3] = "$" + acc.getBalance();
            if (headerWidth.get(headers[3]) < table[rowIndex][3].length()) {
                headerWidth.replace(headers[3], table[rowIndex][3].length());
            }
            table[rowIndex][4] = acc.getStatus();
            if (headerWidth.get(headers[4]) < acc.getStatus().length()) {
                headerWidth.replace(headers[4], acc.getStatus().length());
            }
            rowIndex++;
        }

        for (int i = 0; i < rowIndex; i++) {
            if (i == 0) { // print border line before header
                for (String header : headers) {
                    int headerWidthValue = headerWidth.get(header);
                    System.out.print("+");
                    System.out.print("-".repeat(headerWidthValue + 2));
                }
                System.out.println("+");
            }
            for (int j = 0; j < headers.length; j++) {
                System.out.print("| ");
                System.out.printf("%-" + (headerWidth.get(headers[j]) + 1) + "s", table[i][j]); // pad the string with spaces to the right
            }
            System.out.println("|");
            if (i == 0 || i == rowIndex - 1) { // print border line after header and after last row
                for (String header : headers) {
                    int headerWidthValue = headerWidth.get(header);
                    System.out.print("+");
                    System.out.print("-".repeat(headerWidthValue + 2));
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
        for (Account acc : accounts) {
            totalBalance += acc.getBalance();
        }

        return totalBalance;
    }

}
