package transactions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Transaction.
 */
public class Transaction {

    private static int transactionCounter = 0;

    private final String transactionId;
    private final String accountNumber;
    private final String type;
    private final double amount;
    private final double balanceAfter;
    private final String timestamp;

    /**
     * Instantiates a new Transaction.
     *
     * @param accountNumber           the account number
     * @param type                    the type
     * @param amount                  the amount
     * @param balanceAfterTransaction the balance after transaction
     */
    public Transaction(String accountNumber, String type, double amount, double balanceAfterTransaction) {
        this.transactionId = generateTransactionId();
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfterTransaction;
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timestamp = formatter.format(time);
    }


    private static String generateTransactionId() { // Generates a transactionId
        return "TXN" + String.format("%03d", ++transactionCounter);
    }

    /**
     * Gets transaction counter.
     *
     * @return the transaction counter
     */
    public static int getTransactionCounter() {
        return transactionCounter;
    }

    /**
     * Gets account number.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets balance after.
     *
     * @return the balance after
     */
    public double getBalanceAfter() {
        return balanceAfter;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Gets transaction id.
     *
     * @return the transaction id
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }
}
