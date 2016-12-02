package data;

/**
 * This class represents an account of a registered bank client.
 *
 * @author iliyan-kostov
 */
public class BankAccount {

    /**
     * the ID of the account as assigned by the bank database.
     */
    private String id;

    /**
     * the client ID of the owner as assigned by the bank database.
     */
    private String ownerId;

    /**
     * Creates a {@link BankAccount} object by specified account ID and owner
     * ID.
     *
     * @param id the ID of the bank account.
     * @param ownerId the client ID of the owner.
     */
    public BankAccount(String id, String ownerId) {
        this.id = id;
        this.ownerId = ownerId;
    }

    /**
     * Returns the ID of the bank account as assigned by the bank database.
     *
     * @return the ID of the bank account as assigned by the bank database.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the client ID of the owner as assigned by the bank database.
     *
     * @return the client ID of the owner as assigned by the bank database.
     */
    public String getOwnerId() {
        return this.ownerId;
    }
}
