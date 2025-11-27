package transactions;

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

        int[] headerWidth = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            headerWidth[i] = headers[i].length();
        }

        String[][] table = new String[this.transactionCount + 1][headers.length]; // a 2D array to hold table data
        table[0] = headers; // set headers in the first row

        int rowIndex = 1; // skip the header row
        for (int i = this.transactionCount - 1; i >= 0; i--) { // set the with of the columes according to the longest data
            Transaction tx = transactions[i];
            table[rowIndex][0] = tx.getTransactionId();
            if (headerWidth[0] < table[rowIndex][0].length()) {
                headerWidth[0] = table[rowIndex][0].length();
            }
            table[rowIndex][1] = tx.getAccountNumber();
            if (headerWidth[1] < table[rowIndex][1].length()) {
                headerWidth[1] = table[rowIndex][1].length();
            }
            table[rowIndex][2] = tx.getType();
            if (headerWidth[2] < table[rowIndex][2].length()) {
                headerWidth[2] = table[rowIndex][2].length();
            }
            table[rowIndex][3] = (tx.getType().equalsIgnoreCase("DEPOSIT") ? "+$" : "-$") + tx.getAmount();
            if (headerWidth[3] < table[rowIndex][3].length()) {
                headerWidth[3] = table[rowIndex][3].length();
            }

            table[rowIndex][4] = tx.getTimestamp();
            if (headerWidth[4] < table[rowIndex][4].length()) {
                headerWidth[4] = table[rowIndex][4].length();
            }

            rowIndex++;
        }


        // Print the table
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


        int[] headerWidth = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            headerWidth[i] = headers[i].length();
        }

        String[][] table = new String[this.transactionCount + 1][headers.length]; // a 2D array to hold table data
        table[0] = headers; // set headers in the first row

        double totalDeposits = 0;
        double totalWithdrawals = 0;


        int rowIndex = 1; // skip the header row
        for (int i = this.transactionCount - 1; i >= 0; i--) { // set the with of the columes according to the longest data
            Transaction tx = transactions[i];
            if (tx.getAccountNumber().equals(accountNumber)) {
                table[rowIndex][0] = tx.getTransactionId();
                if (headerWidth[0] < table[rowIndex][0].length()) {
                    headerWidth[0] = table[rowIndex][0].length();
                }
                table[rowIndex][1] = tx.getAccountNumber();
                if (headerWidth[1] < table[rowIndex][1].length()) {
                    headerWidth[1] = table[rowIndex][1].length();
                }
                table[rowIndex][2] = tx.getType();
                if (headerWidth[2] < table[rowIndex][2].length()) {
                    headerWidth[2] = table[rowIndex][2].length();
                }
                table[rowIndex][3] = (tx.getType().equalsIgnoreCase("DEPOSIT") ? "+$" : "-$") + tx.getAmount();
                if (headerWidth[3] < table[rowIndex][3].length()) {
                    headerWidth[3] = table[rowIndex][3].length();
                }

                table[rowIndex][4] = tx.getTimestamp();
                if (headerWidth[4] < table[rowIndex][4].length()) {
                    headerWidth[4] = table[rowIndex][4].length();
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

        System.out.println("\nNumber of transactions: " + (rowIndex - 1));
        System.out.println("Total transactions: " + transactionCount);
        System.out.println("Total Deposits: $" + totalDeposits);
        System.out.println("Total Withdrawals: $" + totalWithdrawals);

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }


}
