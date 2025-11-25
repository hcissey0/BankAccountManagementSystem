package transactions;

import java.util.*;

public class TransactionManager {
    private List<Transaction> transactions;
    private int transactionCount;

    public TransactionManager() {
        this.transactions = new ArrayList<Transaction>(200);
        this.transactionCount = 0;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        this.transactionCount++;
    }

    public double calculateTotalDeposits() {
        double total = 0;
        for (Transaction tx : transactions) {
            if (tx.getType().equals("Deposit")) {
                total += tx.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalWithdrawals() {
        double total = 0;
        for (Transaction tx : transactions) {
            if (tx.getType().equals("Withdrawal")) {
                total += tx.getAmount();
            }
        }
        return total;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void viewTransactionsByAccount(String accountNumber) {
        String[] headers = {
                "TRANSACTION ID",
                "ACCOUNT NUMBER",
                "TYPE",
                "AMOUNT",
                "DATE"
        };
        Map<String, Integer> headerWidth = new HashMap<>();

        String[][] table = new String[transactions.size() + 1][headers.length];

        for (String string : headers) {
            headerWidth.put(string, string.length());
        }

        table[0] = headers;

        int rowIndex = 1;
        for (Transaction tx : transactions) {
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
                table[rowIndex][3] = String.valueOf(tx.getAmount());
                if (headerWidth.get(headers[3]) < String.valueOf(tx.getAmount()).length()) {
                    headerWidth.replace(headers[3], String.valueOf(tx.getAmount()).length());
                }
                
                table[rowIndex][4] = tx.getTimestamp();
                if (headerWidth.get(headers[4]) < tx.getTimestamp().length()) {
                    headerWidth.replace(headers[4], tx.getTimestamp().length());
                }
                rowIndex++;
            }
        }

        // Print the table
        for (int i = 0; i < rowIndex; i++) {
            if (i == 0) {
                for (String header : headers) {
                    int headerWidthValue = headerWidth.get(header);
                    System.out.print("+");
                    System.out.print("-".repeat(headerWidthValue + 2));
                }
                System.out.println("+");
            }
            for (int j = 0; j < headers.length; j++) {
                System.out.print("| ");
                System.out.print(String.format("%-" + (headerWidth.get(headers[j]) + 1) + "s", table[i][j]));
            }
            System.out.println("|");
            if (i == 0 || i == rowIndex - 1) {
                for (String header : headers) {
                    int headerWidthValue = headerWidth.get(header);
                    System.out.print("+");
                    System.out.print("-".repeat(headerWidthValue + 2));
                }
                System.out.println("+");
            }
        }
    }



}
