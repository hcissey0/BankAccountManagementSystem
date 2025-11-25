import accounts.Account;
import accounts.AccountManager;
import accounts.CheckingAccount;
import accounts.SavingsAccount;
import customers.Customer;
import customers.PremiumCustomer;
import customers.RegularCustomer;
import transactions.Transaction;
import transactions.TransactionManager;

import java.util.*;
class Main {


    public static void main() {
        System.out.println("+-------------------------------------+");
        System.out.println("| BANK ACCOUNT MANAGEMENT - MAIN MENU |");
        System.out.println("+-------------------------------------+");
        AccountManager accountManager = new AccountManager();
        TransactionManager transactionManager = new TransactionManager();
        
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n\nMain Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Process Transactions");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit\n\n");

            // Read the user input
            choice = readInt(scanner, "Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    createAccount(accountManager, scanner);
                    break;
                case 2:
                    viewAccounts(accountManager);
                    break;
                case 3:
                    processTransaction(accountManager, transactionManager, scanner);
                    break;
                case 4:
                    viewTransactionHistory(transactionManager, scanner);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\nInvalid Input. Try Again!\n");
            }
        } while (choice != 5);



        scanner.close();

        System.out.println("Thank you for using Bank Account Management System!");
        System.out.println("Goodbye!");
    }

    public static void viewTransactionHistory(TransactionManager transactionManager, Scanner scanner) {
        System.out.println();
        System.out.println("+--------------------------+");
        System.out.println("| VIEW TRANSACTION HISTORY |");
        System.out.println("+--------------------------+");

        String accountNumber = readString(scanner, "\nEnter Account number: ");

        transactionManager.viewTransactionsByAccount(accountNumber);
    }

    private static void processTransaction(AccountManager accountManager, TransactionManager transactionManager, Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------+");
        System.out.println("| PROCESS TRANSACTION |");
        System.out.println("+---------------------+");

        String accountNumber = readString(scanner, "\nEnter Account number: ");

        Account account = accountManager.findAccount(accountNumber);

        if (account == null) {
            System.out.println("❌Account not found!");
            return;
        }
        account.displayAccountDetails();

        System.out.println("\nSelect Transaction Type:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        
        int transactionType = readInt(scanner, "Enter choice (1-2): ", 1, 2);

        double amount = readDouble(scanner, "Enter amount: ", 0);
        double amountAfter = 0;

        if (transactionType == 1) {
            if (amount <= 0) {
                System.out.println("❌Deposit amount must be positive!");
                return;
            }
            amountAfter = account.deposit(amount);
            System.out.println("✅Deposit Successful! New Balance: $" + account.getBalance());
        } else if (transactionType == 2) {
            amountAfter = account.withdraw(amount);
            if (amountAfter > 0) {
                System.out.println("✅Withdrawal Successful! New Balance: $" + account.getBalance());
            } else {
                System.out.println("❌Insufficient funds or minimum balance requirement not met!");
            }
        } else {
            System.out.println("❌Invalid transaction type selected!");
        }
        Transaction transaction = new Transaction(
                account.getAccountNumber(),
                (transactionType == 1) ? "Deposit" : "Withdrawal",
                amount,
                amountAfter       );
        transactionManager.addTransaction(transaction);

    }


    public static void createAccount(AccountManager accountManager, Scanner scanner) {
        System.out.println();
        System.out.println("+------------------+");
        System.out.println("| ACCOUNT CREATION |");
        System.out.println("+------------------+");

        Customer customer = createCustomer(scanner);
        Account account = createAccountForCustomer(scanner, customer);

        accountManager.addAccount(account);

        System.out.println("✅Account Created Successfully!");
    }

    private static Customer createCustomer(Scanner scanner) {
        String name = readString(scanner, "\n\nEnter customer name: ");
        int age = readInt(scanner, "Enter customer age: ", 0, 150);
        String contact = readString(scanner, "Enter customer contact: ");
        String address = readString(scanner, "Enter customer address: ");

        System.out.println("\nCustomer type:");
        System.out.println("1. Regular Customer (Standard banking services)");
        System.out.println("2. Premium Customer (Enhanced benefits, min balance $10,000)");

        int customerType = readInt(scanner, "Select type (1-2): ", 1, 2);

        if (customerType == 1) {
            return new RegularCustomer(name, age, contact, address);
        } else {
            return new PremiumCustomer(name, age, contact, address);
        }
    }

    private static Account createAccountForCustomer(Scanner scanner, Customer customer) {
        System.out.println("\nAccount type:");
        System.out.println("1. Savings Account (Interest: 3.5%, Min Balance: $500)");
        System.out.println("2. Checking Account (Overdraft: $1,00, Monthly Fee: $10)");

        int accountType = readInt(scanner, "Select type (1-2): ", 1, 2);

        double initialDeposit = readDouble(scanner, "\nEnter initial deposit amount: ", 0);

        if (accountType == 1) {
            return new SavingsAccount(customer, initialDeposit);
        } else {
            return new CheckingAccount(customer, initialDeposit);
        }
    }

    public static void viewAccounts(AccountManager accountManager) {
        accountManager.viewAllAccounts();
    }

    // input validation metods
    private static int readInt(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }

    private static double readDouble(Scanner scanner, String prompt, double min) {
        double value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number greater than or equal to " + min + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }

    private static String readString(Scanner scanner, String prompt) {
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                break;
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
        return value;
    }
}