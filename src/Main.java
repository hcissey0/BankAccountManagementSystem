import accounts.Account;
import accounts.CheckingAccount;
import accounts.SavingsAccount;
import customers.Customer;
import customers.PremiumCustomer;
import customers.RegularCustomer;
import managers.AccountManager;

import java.util.*;
class Main {


    public static void main() {
        AccountManager accountManager = new AccountManager();
        System.out.println("________________________________________");
        System.out.println("| BANK ACCOUNT MANAGEMENT - MAIN MENU  |");
        System.out.println("|______________________________________|");

        int choice = 0;
        do {
            System.out.println("\n\nMain Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Process Transactions");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit\n\n");

            System.out.print("Enter your choice: ");

            // Read the user input
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(accountManager);
                    break;
                case 2:
                    accountManager.viewAllAccounts();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\nInvalid Input. Try Again!\n");
            }
        } while (choice != 5);

        System.out.println("Thank you for using Bank Account Management System!");
        System.out.println("Goodbye!");
    }

    public static void createAccount(AccountManager accountManager) {
        System.out.println("ACCOUNT CREATION");
        System.out.println("-----------------------------------------");

        Scanner scanner = new Scanner(System.in);

        String name, contact, address;
        int age, customerType, accountType;
        double initialDeposit;


        System.out.print("\n\nEnter customer name: ");
        name = scanner.nextLine();
        System.out.print("Enter customer age: ");
        age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter customer contact: ");
        contact = scanner.nextLine();
        System.out.print("Enter customer address: ");
        address = scanner.nextLine();

        do {

            System.out.println("\nCustomer type:");
            System.out.println("1. Regular Customer (Standard banking services)");
            System.out.println("2. Premium Customer (Enhanced benefits, min balance $10,000)");

            System.out.print("Select type (1-2): ");
            customerType = scanner.nextInt();
            if (customerType < 1 || customerType > 2) {
                System.out.println("Invalid input. Try Again.");
            }
        } while (customerType > 2);

        do {

            System.out.println("\nAccount type:");
            System.out.println("1. Savings Account (Interest: 3.5%, Min Balance: $500)");
            System.out.println("2. Checking Account (Overdraft: $1,00, Monthly Fee: $10)");

            System.out.print("Select type (1-2): ");
            accountType = scanner.nextInt();
            if (accountType < 1 || accountType > 2) {
                System.out.println("Invalid input. Try Again.\n");
            }
        } while (accountType > 2);


        System.out.print("\nEnter initial deposit amount: ");
        initialDeposit = scanner.nextDouble();

        Customer customer;
        if (customerType == 1) {
            customer = new RegularCustomer(name, age, contact, address);
        } else {
            customer = new PremiumCustomer(name, age, contact, address);
        }

        Account account;
        if (accountType == 1) {
            account = new SavingsAccount(customer);
        } else {
            account = new CheckingAccount(customer);
        }

        accountManager.addAccount(account);

        System.out.println("✅Account Created Successfully!");


    }

    public static void veiwAccounts(AccountManager accountManager) {
        System.out.println("ACCOUNT LISTING");
        System.out.println("_________________________________");



    }

    public static boolean isCapital(char a) {
        return (int) a > 63 && (int) a < 90;
    }

    // columns: must be fields of the objects to show e.g: ["accountNumber", "customer"]
    public static void printTable(String[] columns, Object[] rows) {
        int tableWidth;
        int[] columnWidths = new int[columns.length];


        String[] columnHeadings = new String[columns.length];
        for (int a = 0; a < columns.length; a++) {
            StringBuffer colName = new StringBuffer("");
            for (int i = 0; i < columns[a].length(); i++) {
                colName.append(columns[a].charAt(i));
                if (i + 1 < columns.length && isCapital(columns[a].charAt(i+1))) {
                    colName.append(" ");
                }
            }
            columnHeadings[a] = colName.toString().toUpperCase();
            
        }

        System.out.println(columnHeadings);

    }
}