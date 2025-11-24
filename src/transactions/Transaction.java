package transactions;

import java.time.*;
import java.time.format.*;

public class Transaction implements Transactable {

    private static int transactionCounter = 0;

    private final String transactionId;
    private String accountNumber;
    private String type;
    private double amount;
    private double balanceAfter;
    private String timestamp;

    Transaction(String accountNumber, String type, double amount, double balanceAfterTransaction) {
        this.transactionId = generateTransactionId();
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfterTransaction;
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timestamp = formatter.format(time);
    }


    private String generateTransactionId() {
        return "TXN" + String.format("%03d", transactionCounter++);
    }

    @java.lang.Override
    public boolean processTransaction(double amount, String type) {
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public static int getTransactionCounter() {
        return transactionCounter;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }
}
