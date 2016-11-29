package networking_and_security.messages;

/**
 * A {@link Login_Request} is sent by a client that has already been registered
 * in the system and attempts to connect to the server using the already
 * registered user account.
 *
 * @author iliyan-kostov
 */
public class Login_Request extends Message {

    /**
     * the username provided by the client.
     */
    protected String username;

    /**
     * the password provided by the client.
     */
    protected String password;

    /**
     * Creates a {@link Login_Request} according to the arguments provided.
     *
     * @param username the username provided by the client.
     *
     * @param password the password provided by the client.
     */
    public Login_Request(String username, String password) {
        super(Type.AUTHENTICATION_LOGIN_REQUEST, true);
        this.username = username;
        this.password = password;
    }

    /**
     * returns the username provided by the client.
     *
     * @return the username provided by the client
     */
    public final String getUsername() {
        return this.username;
    }

    /**
     * returns the password provided by the client.
     *
     * @return the password provided by the client
     */
    public final String getPassword() {
        return this.password;
    }
}
