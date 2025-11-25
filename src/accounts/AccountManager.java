package accounts;

import java.util.*;

public class AccountManager {

    private List<Account> accounts;
    private int accountCount;

    public AccountManager() {
        this.accounts = new ArrayList<Account>(50);
        this.accountCount = 0;
    }

    public int getAccountCount() {
        return accountCount;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
        this.accountCount++;
    }

    public Account findAccount(String accountNumber) {
        for (Account acc: accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public void viewAllAccounts() {
        String[] headers = {
                "ACCOUNT NUMBER",
                "CUSTOMER NAME",
                "TYPE",
                "BALANCE",
                "STATUS"
        };
        Map<String, Integer> headerWidth = new HashMap<>(); // a map of column widths with header names as keys

        String[][] table = new String[accounts.size() + 1][headers.length]; // a 2D array to hold table data

        for (String string : headers) {
            headerWidth.put(string, string.length());
        }

        
        table[0] = headers;
        
        for (int i = 0; i < accounts.size(); i++) { // set the with of the columes according to the longest data, and add data to the table
            Account acc = accounts.get(i);
            table[i + 1][0] = acc.getAccountNumber();
            if (headerWidth.get(headers[0]) < acc.getAccountNumber().length()) {
                headerWidth.replace(headers[0], acc.getAccountNumber().length());
            }
            table[i + 1][1] = acc.getCustomer().getName();
            if (headerWidth.get(headers[1]) < acc.getCustomer().getName().length()) {
                headerWidth.replace(headers[1], acc.getCustomer().getName().length());
            }
            table[i + 1][2] = acc.getAccountType();
            if (headerWidth.get(headers[2]) < acc.getAccountType().length()) {
                headerWidth.replace(headers[2], acc.getAccountType().length());
            }
            table[i + 1][3] = String.valueOf(acc.getBalance());
            if (headerWidth.get(headers[3]) < String.valueOf(acc.getBalance()).length()) {
                headerWidth.replace(headers[3], String.valueOf(acc.getBalance()).length());
            }
            table[i + 1][4] = acc.getStatus();
            if (headerWidth.get(headers[4]) < acc.getStatus().length()) {
                headerWidth.replace(headers[4], acc.getStatus().length());
            }

        }

        for (String header: headers) { // print border line before header
            int headerWidthValue = headerWidth.get(header);
            System.out.print("+");
            System.out.print("-".repeat(headerWidthValue + 2));
        }

        
        System.out.println("+");
        for (String[] row: table) { // print table rows
            for (String cell: row) {
                if (row[0] == cell) {
                    System.out.print("| ");
                }
                System.out.print(cell);
                System.out.print(" ".repeat(headerWidth.get(headers[Arrays.asList(row).indexOf(cell)]) - cell.length()));
                System.out.print(" | ");
            }
            if (row == table[0] || row == table[table.length - 1]) {
                System.out.println();
                for (String header: headers) {
                    int headerWidthValue = headerWidth.get(header);
                    System.out.print("+");
                    System.out.print("-".repeat(headerWidthValue + 2));
                }
                System.out.println("+");
            } else {
                System.out.println();
            }

        }

    }

    public double getTotalBalance() {
        double totalBalance = 0;
        for (Account acc: accounts) {
            totalBalance += acc.getBalance();
        }

        return totalBalance;
    }

}
