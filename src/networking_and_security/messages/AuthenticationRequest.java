package networking_and_security.messages;

/**
 * This is an abstract class that represents an authentication {@link Request}.
 * The client submits username and password for verification.
 *
 * @author iliyan-kostov
 */
public abstract class AuthenticationRequest extends Request {

    /**
     * the username provided by the client.
     */
    private String username;

    /**
     * the password provided by the client.
     */
    private String password;

    /**
     * Constructs a {@link TransactionRequest} using the provided arguments.
     *
     * @param type the type of the message.
     *
     * @param username the username provided by the client.
     *
     * @param password the password provided by the client.
     */
    public AuthenticationRequest(TYPE type, String username, String password) {
        super(type);
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username provided by the client.
     *
     * @return the username provided by the client.
     */
    public final String getUsername() {
        return this.username;
    }

    /**
     * Returns the password provided by the client.
     *
     * @return the password provided by the client.
     */
    public final String getPassword() {
        return this.password;
    }
}
