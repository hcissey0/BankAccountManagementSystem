import accounts.Account;
import accounts.AccountManager;
import accounts.CheckingAccount;
import accounts.SavingsAccount;
import customers.Customer;
import customers.PremiumCustomer;
import customers.RegularCustomer;
import transactions.Transaction;
import transactions.TransactionManager;

import java.util.Scanner;


/**
 * The type Main.
 */
class Main {


    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        System.out.println("+-------------------------+");
        System.out.println("| BANK ACCOUNT MANAGEMENT |");
        System.out.println("+-------------------------+");
        
        // create an account manager and a transaction manager.
        AccountManager accountManager = new AccountManager();
        TransactionManager transactionManager = new TransactionManager();

        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println();
            System.out.println("+-----------+");
            System.out.println("| MAIN MENU |");
            System.out.println("+-----------+");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Process Transaction");
            System.out.println("4. View Transaction History for an account");
            System.out.println("5. View all Transaction Histories");
            System.out.println("6. Exit\n");

            choice = readInt(scanner, "Enter your choice: ", 1, 6);

            switch (choice) {
                case 1:
                    createAccount(accountManager, scanner);
                    break;
                case 2:
                    viewAccounts(accountManager, scanner);
                    break;
                case 3:
                    processTransaction(accountManager, transactionManager, scanner);
                    break;
                case 4:
                    viewTransactionHistory(transactionManager, scanner);
                    break;
                case 5:
                    viewAllTransactionHistory(transactionManager, scanner);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("\nInvalid Input. Try Again!\n");
            }
        } while (choice != 6);

        scanner.close();

        System.out.println("Thank you for using Bank Account Management System!");
        System.out.println("Goodbye!");
    }

    /**
     * View all transaction history.
     *
     * @param transactionManager the transaction manager
     * @param scanner            the scanner
     */
    public static void viewAllTransactionHistory(TransactionManager transactionManager, Scanner scanner) {
        transactionManager.viewAllTransactions(scanner);
    }

    /**
     * View transaction history.
     *
     * @param transactionManager the transaction manager
     * @param scanner            the scanner
     */
    public static void viewTransactionHistory(TransactionManager transactionManager, Scanner scanner) {
        System.out.println();
        System.out.println("+--------------------------+");
        System.out.println("| VIEW TRANSACTION HISTORY |");
        System.out.println("+--------------------------+");

        String accountNumber = readString(scanner, "\nEnter Account number: ");

        transactionManager.viewTransactionsByAccount(accountNumber, scanner);
    }


    /**
     * Process transaction.
     *
     * @param accountManager     the account manager
     * @param transactionManager the transaction manager
     * @param scanner            the scanner
     */
    public static void processTransaction(AccountManager accountManager, TransactionManager transactionManager, Scanner scanner) {
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

        String typeStr = (transactionType == 1) ? "Deposit" : "Withdrawal";

        double amountAfter = (transactionType == 1 ? account.getBalance() + amount : account.getBalance() - amount);
        Transaction transaction = new Transaction(
                account.getAccountNumber(),
                (transactionType == 1) ? "DEPOSIT" : "WITHDRAWAL",
                amount,
                amountAfter);


        System.out.println();
        System.out.println("+--------------------------+");
        System.out.println("| Transaction Confirmation |");
        System.out.println("+--------------------------+");
        System.out.println("Transaction ID: " + transaction.getTransactionId());
        System.out.println("Account: " + account.getAccountNumber());
        System.out.println("Type: " + typeStr.toUpperCase());
        System.out.println("Amount: $" + amount);
        System.out.println("Previous Balance: $" + account.getBalance());
        System.out.println("New Balance: $" + amountAfter);
        System.out.println("Date/Time: " + transaction.getTimestamp());

        System.out.print("\nConfirm transaction? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!confirm.startsWith("y")) {
            System.out.println("❌Transaction cancelled.");
            transaction = null;
            return;
        }

        boolean success = false;

        if (transactionType == 1) {
            success = account.processTransaction(amount, "Deposit");
            if (success) {
                System.out.println("✅Deposit Successful! New Balance: $" + account.getBalance());
                amountAfter = account.getBalance();
            } else {
                System.out.println("❌Deposit failed! Amount must be positive.");
            }
        } else if (transactionType == 2) {
            success = account.processTransaction(amount, "Withdrawal");
            if (success) {
                System.out.println("✅Withdrawal Successful! New Balance: $" + account.getBalance());
                amountAfter = account.getBalance();
            } else {
                System.out.println("❌Insufficient funds or minimum balance requirement not met!");
            }
        } else {
            System.out.println("❌Invalid transaction type selected!");
        }

        if (success) {
            transactionManager.addTransaction(transaction);
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();

    }


    /**
     * Create account.
     *
     * @param accountManager the account manager
     * @param scanner        the scanner
     */
    public static void createAccount(AccountManager accountManager, Scanner scanner) {
        System.out.println();
        System.out.println("+------------------+");
        System.out.println("| ACCOUNT CREATION |");
        System.out.println("+------------------+");

        Customer customer = createCustomer(scanner);
        Account account = createAccountForCustomer(scanner, customer);

        System.out.println();
        System.out.println("+--------------+");
        System.out.println("| Confirmation |");
        System.out.println("+--------------+");
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Customer Type: " + (customer instanceof RegularCustomer ? "Regular" : "Premium"));
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Initial Deposit: $" + account.getBalance());

        System.out.print("\nConfirm account creation? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!confirm.startsWith("y")) {
            System.out.println("❌Account creation cancelled.");
            return;
        }

        accountManager.addAccount(account);

        System.out.println("✅Account Created Successfully!");

        account.displayAccountDetails();
        customer.displayCustomerDetails();

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();

    }

    private static Customer createCustomer(Scanner scanner) {
        String name = readString(scanner, "\nEnter customer name: ");
        int age = readInt(scanner, "Enter customer age: ", 0, 150);
        String contact = readString(scanner, "Enter customer contact: ");
        String address = readString(scanner, "Enter customer address: ");

        System.out.println("\nCustomer type:");
        System.out.println("1. Regular Customer (Standard banking services)");
        System.out.println("2. Premium Customer (Enhanced benefits, min balance $10,000)");

        int customerType = readInt(scanner, "\nSelect type (1-2): ", 1, 2);

        if (customerType == 1) {
            return new RegularCustomer(name, age, contact, address);
        } else {
            return new PremiumCustomer(name, age, contact, address);
        }
    }

    private static Account createAccountForCustomer(Scanner scanner, Customer customer) {
        System.out.println("\nAccount type:");
        System.out.println("1. Savings Account (Interest: 3.5%, Min Balance: $500)");
        System.out.println("2. Checking Account (Overdraft: $1,000, Monthly Fee: $10)");

        int accountType = readInt(scanner, "\nSelect type (1-2): ", 1, 2);

        double minDeposit = (accountType == 1) ? 500.0 : 0.0;
        if (customer instanceof PremiumCustomer) {
            minDeposit = Math.max(minDeposit, 10000.0);
        }

        double initialDeposit = readDouble(scanner, "\nEnter initial deposit amount: ", minDeposit);

        if (accountType == 1) {
            return new SavingsAccount(customer, initialDeposit);
        } else {
            return new CheckingAccount(customer, initialDeposit);
        }
    }

    /**
     * View accounts.
     *
     * @param accountManager the account manager
     * @param scanner        the scanner
     */
    public static void viewAccounts(AccountManager accountManager, Scanner scanner) {
        accountManager.viewAllAccounts(scanner);
    }

    // input validation functions
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