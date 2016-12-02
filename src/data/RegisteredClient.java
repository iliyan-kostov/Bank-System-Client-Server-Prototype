package data;

/**
 * This class represents a client of the banking system that has already been
 * registered and his account has been verified and assigned a valid client ID
 * by the bank database.
 *
 * @author iliyan-kostov
 */
public class RegisteredClient {

    /**
     * the client ID as assigned by the bank database.
     */
    private String id;

    /**
     * Creates a client object with the specified ID (assigned according to the
     * bank database).
     *
     * @param id the ID to assign to the client according to the bank database.
     */
    public RegisteredClient(String id) {
        this.id = id;
    }

    /**
     * Returns the client ID as assigned by the bank database.
     *
     * @return the client ID as assigned by the bank database.
     */
    public String getId() {
        return this.id;
    }
}
