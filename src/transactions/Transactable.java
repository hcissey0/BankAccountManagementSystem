package transactions;

/**
 * The interface Transactable.
 */
public interface Transactable {
    /**
     * Process transaction boolean.
     *
     * @param amount the amount
     * @param type   the type
     * @return the boolean
     */
    boolean processTransaction(double amount, String type);
}
