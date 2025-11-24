package managers;

import accounts.Account;
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
            if (acc.getAccountNumber() == accountNumber) {
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
//        HashMap<String, Integer> headerWidth = new HashMap<String, Integer>();
//        headerWidth.put(headers[0], headers[0].length());
//        headerWidth.put(headers[1], headers[1].length());
//        // get longest in customer name
//        for (Account acc: accounts)
//            if (acc.getCustomer().getName().length() > headers[1].length())
//                headerWidth.setValue(headers[1], acc.getCustomer().getName().length());
//
//        headerWidth.put(headers[2], "Checking".length()); // using checking length since it is the longest.
//        headerWidth.put(headers[3], headers[3].length());
//        // set the longest balance
//        for (Account acc: accounts)
//            if (acc.getBalance().toString().length() > headers[3].length())
//                headerWidth.set(headers[3], acc.getBalance().toString().length());
//        headerWidth.put(headers[4], headers[4].length());

        for (String h: headers) {
            if (h == headers[0]) {
                System.out.print("| ");
            }
            System.out.print(h);

//            System.out.print(" ".repeat(headerWidth.get(h) - h.length()));
            System.out.print(" | ");
        }
        System.out.println();

        for (Account acc: accounts) {
            System.out.print("| ");
            System.out.print(acc.getAccountNumber());
            if (acc.getAccountNumber().length() < headers[0].length())
                System.out.print(" ".repeat(headers[0].length()-acc.getAccountNumber().length()));
            System.out.print(" | ");
            System.out.print(acc.getCustomer().getName());
            System.out.print(" | ");
            System.out.print(acc.getAccountType());
            System.out.print(" | ");
            System.out.print(acc.getBalance());
            System.out.print(" | ");
            System.out.print(acc.getStatus());
            System.out.print(" | ");
            System.out.println();
        }


    }

    private boolean isCapital(char a) {
        return (int) a > 64 && (int) a < 91;
    }

    private String fieldToHeader(String field) {
        StringBuffer headerName = new StringBuffer("");
        for (int i = 0; i < field.length(); i++) {
            headerName.append(field.charAt(i));
            if (i + 1 < field.length() && isCapital(field.charAt(i + 1))) {
                headerName.append(" ");
            }
        }
        return headerName.toString().toUpperCase();
    }

    public double getTotalBalance() {
        double totalBalance = 0;
        for (Account acc: accounts) {
            totalBalance += acc.getBalance();
        }

        return totalBalance;
    }

}
