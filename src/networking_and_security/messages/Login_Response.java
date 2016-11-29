package networking_and_security.messages;

/**
 * A {@link Login_Response} is returned by the server to the client after a
 * client attempts to connect to the server using an already registered user
 * account.
 *
 * @author iliyan-kostov
 */
public class Login_Response extends Message {

    /**
     * whether the attempted login was successful.
     */
    protected boolean successful;

    /**
     * Creates a {@link Login_Response} according to the arguments provided.
     *
     * @param successful whether the attempted login was successful.
     */
    public Login_Response(boolean successful) {
        super(Type.AUTHENTICATION_LOGIN_RESPONSE, false);
        this.successful = successful;
    }

    /**
     * returns whether the attempted login was successful.
     *
     * @return true if the attempted login was successful, false otherwise.
     */
    public final boolean isSuccessful() {
        return this.successful;
    }
}
