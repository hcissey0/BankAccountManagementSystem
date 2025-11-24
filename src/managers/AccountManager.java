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
        int width = 0;
        for (String h: headers) {
            width += h.length();
        }
        System.out.println("_".repeat(width));


    }

    public double getTotalBalance() {
        double totalBalance = 0;
        for (Account acc: accounts) {
            totalBalance += acc.getBalance();
        }

        return totalBalance;
    }

}
