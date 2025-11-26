# Bank Account Management System

A console-based application designed to simulate core banking operations. This system allows for the management of customers, accounts, and transactions with a focus on object-oriented programming principles.

## Features

*   **Account Management**:
    *   Create **Savings Accounts** (Interest-bearing, minimum balance).
    *   Create **Checking Accounts** (Overdraft protection, monthly fees).
    *   View all registered accounts in a formatted table.
*   **Customer Management**:
    *   Support for **Regular** and **Premium** customers.
    *   Capture customer details (Name, Age, Contact, Address).
*   **Transaction Processing**:
    *   **Deposit** and **Withdraw** funds.
    *   Real-time balance updates.
    *   Transaction validation (e.g., sufficient funds, positive amounts).
    *   **Confirmation prompts** before processing transactions.
*   **Transaction History**:
    *   View detailed transaction logs for specific accounts.
    *   View a master log of all system transactions.
    *   Transactions displayed in **reverse chronological order** (newest first).
*   **Robust Input Handling**:
    *   Input validation for numbers and text.
    *   Error handling for invalid menu selections or data types.

## Project Structure

The project is organized into logical packages:

*   `src/Main.java`: The entry point of the application, handling the main menu and user interaction.
*   `src/accounts/`: Contains account-related logic.
    *   `Account.java`: Abstract base class for accounts.
    *   `SavingsAccount.java`: Implementation of savings logic (interest, min balance).
    *   `CheckingAccount.java`: Implementation of checking logic (overdraft, fees).
    *   `AccountManager.java`: Manages the collection of accounts.
*   `src/customers/`: Contains customer-related logic.
    *   `Customer.java`: Base class for customer data.
    *   `RegularCustomer.java` & `PremiumCustomer.java`: Specialized customer types.
*   `src/transactions/`: Handles transaction records.
    *   `Transaction.java`: Represents a single transaction event.
    *   `TransactionManager.java`: Manages the history and retrieval of transactions.
    *   `Transactable.java`: Interface defining transaction behaviors.

## Technologies & Concepts

*   **Language**: Java (JDK 21+)
*   **OOP Principles**:
    *   **Encapsulation**: Private fields with getters/setters.
    *   **Inheritance**: Extending `Account` and `Customer` classes.
    *   **Polymorphism**: Handling different account types uniformly.
    *   **Abstraction**: Abstract classes and Interfaces (`Transactable`).

## How to Run

1.  **Prerequisites**: Ensure you have Java Development Kit (JDK) 21 or higher installed.
2.  **Compile**:
    Navigate to the `src` directory and compile the Java files.
    ```bash
    javac Main.java
    ```
3.  **Run**:
    Start the application.
    ```bash
    java Main
    ```

## Usage

Follow the on-screen prompts to navigate the menu:
1.  Select an option by entering the corresponding number.
2.  Input requested data (names, amounts, etc.).
3.  Confirm actions when prompted (y/n).
