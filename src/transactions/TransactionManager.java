package transactions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The type Transaction manager.
 */
public class TransactionManager {
    private final Transaction[] transactions;
    private int transactionCount;

    /**
     * Instantiates a new Transaction manager.
     */
    public TransactionManager() {
        this.transactions = new Transaction[200];
        this.transactionCount = 0;
    }

    /**
     * Add transaction.
     *
     * @param transaction the transaction
     */
    public void addTransaction(Transaction transaction) {
        this.transactions[this.transactionCount++] = transaction;
    }

    /**
     * Calculate total deposits double.
     *
     * @return the double
     */
    public double calculateTotalDeposits() {
        double total = 0;

        for (int i = 0; i < this.transactionCount; i++)
            if (this.transactions[i].getType().equals("Deposit"))
                total += this.transactions[i].getAmount();

        return total;
    }

    /**
     * Calculate total withdrawals.
     *
     * @return the double
     */
    public double calculateTotalWithdrawals() {
        double total = 0;

        for (int i = 0; i < this.transactionCount; i++)
            if (this.transactions[i].getType().equals("Withdrawal"))
                total += this.transactions[i].getAmount();

        return total;
    }

    /**
     * Gets transaction count.
     *
     * @return the transaction count
     */
    public int getTransactionCount() {
        return this.transactionCount;
    }

    /**
     * View all transactions.
     *
     * @param scanner the scanner to use for "Press Enter to continue..." feature
     */
    public void viewAllTransactions(Scanner scanner) {
        String[] headers = {
                "TRANSACTION ID",
                "ACCOUNT NUMBER",
                "TYPE",
                "AMOUNT",
                "DATE"
        };

        if (this.transactionCount == 0) {
            System.out.println();
            System.out.println("+----------------------------+");
            System.out.println("| No transactions available. |");
            System.out.println("+----------------------------+");
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            return;
        }

        Map<String, Integer> headerWidth = new HashMap<>(); // a map of column widths with header names as keys


        String[][] table = new String[this.transactionCount + 1][headers.length]; // a 2D array to hold table data


        for (String string : headers) {
            headerWidth.put(string, string.length());
        }

        table[0] = headers; // set headers in the first row


        int rowIndex = 1; // skip the header row
        for (int i = this.transactionCount - 1; i >= 0; i--) { // set the with of the columes according to the longest data
            Transaction tx = transactions[i];
            table[rowIndex][0] = tx.getTransactionId();
            if (headerWidth.get(headers[0]) < tx.getTransactionId().length()) {
                headerWidth.replace(headers[0], tx.getTransactionId().length());
            }
            table[rowIndex][1] = tx.getAccountNumber();
            if (headerWidth.get(headers[1]) < tx.getAccountNumber().length()) {
                headerWidth.replace(headers[1], tx.getAccountNumber().length());
            }
            table[rowIndex][2] = tx.getType();
            if (headerWidth.get(headers[2]) < tx.getType().length()) {
                headerWidth.replace(headers[2], tx.getType().length());
            }
            table[rowIndex][3] = (tx.getType().equalsIgnoreCase("DEPOSIT") ? "+$" : "-$") + tx.getAmount();
            if (headerWidth.get(headers[3]) < String.valueOf(tx.getAmount()).length() + 2) {
                headerWidth.replace(headers[3], String.valueOf(tx.getAmount()).length() + 2);
            }

            table[rowIndex][4] = tx.getTimestamp();
            if (headerWidth.get(headers[4]) < tx.getTimestamp().length()) {
                headerWidth.replace(headers[4], tx.getTimestamp().length());
            }

            rowIndex++;
        }


        // Print the table
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

        System.out.println("\nNumber of transactions: " + (rowIndex - 1));
        System.out.println("Total transactions: " + transactionCount);
        System.out.println("Total Deposits: $" + this.calculateTotalDeposits());
        System.out.println("Total Withdrawals: $" + this.calculateTotalWithdrawals());

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /**
     * View transactions by account.
     *
     * @param accountNumber the account number
     * @param scanner       the scanner for "Press Enter to continue..." feature
     */
    public void viewTransactionsByAccount(String accountNumber, Scanner scanner) {
        String[] headers = {
                "TRANSACTION ID",
                "ACCOUNT NUMBER",
                "TYPE",
                "AMOUNT",
                "DATE"
        };


        Map<String, Integer> headerWidth = new HashMap<>(); // a map of column widths with header names as keys


        String[][] table = new String[this.transactionCount + 1][headers.length]; // a 2D array to hold table data


        for (String string : headers) {
            headerWidth.put(string, string.length());
        }

        table[0] = headers; // set headers in the first row

        double totalDeposits = 0;
        double totalWithdrawals = 0;


        int rowIndex = 1; // skip the header row
        for (int i = this.transactionCount - 1; i >= 0; i--) { // set the with of the columes according to the longest data
            Transaction tx = transactions[i];
            if (tx.getAccountNumber().equals(accountNumber)) {
                table[rowIndex][0] = tx.getTransactionId();
                if (headerWidth.get(headers[0]) < tx.getTransactionId().length()) {
                    headerWidth.replace(headers[0], tx.getTransactionId().length());
                }
                table[rowIndex][1] = tx.getAccountNumber();
                if (headerWidth.get(headers[1]) < tx.getAccountNumber().length()) {
                    headerWidth.replace(headers[1], tx.getAccountNumber().length());
                }
                table[rowIndex][2] = tx.getType();
                if (headerWidth.get(headers[2]) < tx.getType().length()) {
                    headerWidth.replace(headers[2], tx.getType().length());
                }
                table[rowIndex][3] = (tx.getType().equalsIgnoreCase("DEPOSIT") ? "+$" : "-$") + tx.getAmount();
                if (headerWidth.get(headers[3]) < String.valueOf(tx.getAmount()).length() + 2) {
                    headerWidth.replace(headers[3], String.valueOf(tx.getAmount()).length() + 2);
                }

                table[rowIndex][4] = tx.getTimestamp();
                if (headerWidth.get(headers[4]) < tx.getTimestamp().length()) {
                    headerWidth.replace(headers[4], tx.getTimestamp().length());
                }

                if (tx.getType().equals("DEPOSIT"))
                    totalDeposits += tx.getAmount();
                else
                    totalWithdrawals += tx.getAmount();
                rowIndex++;
            }
        }

        if (rowIndex - 1 == 0) {
            System.out.println();
            System.out.println("+--------------------------------------------+");
            System.out.println("| No transactions recorded for this account. |");
            System.out.println("+--------------------------------------------+");
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            return;
        }

        // Print the table
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

        System.out.println("\nNumber of transactions: " + (rowIndex - 1));
        System.out.println("Total transactions: " + transactionCount);
        System.out.println("Total Deposits: $" + totalDeposits);
        System.out.println("Total Withdrawals: $" + totalWithdrawals);

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }


}
