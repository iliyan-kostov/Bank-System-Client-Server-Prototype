package networking_and_security.messages;

/**
 * A {@link Register_Response} is returned by the server to the client after a
 * client attempts to register a new user account.
 *
 * @author iliyan-kostov
 */
public class Register_Response extends Message {

    /**
     * whether the attempted registration was successful.
     */
    protected boolean successful;

    /**
     * Creates a {@link Register_Response} according to the arguments provided.
     *
     * @param successful whether the attempted login was successful.
     */
    public Register_Response(boolean successful) {
        super(Message.Type.AUTHENTICATION_REGISTER_RESPONSE, false);
        this.successful = successful;
    }

    /**
     * returns whether the attempted registration was successful.
     *
     * @return true if the attempted registration was successful, false
     * otherwise.
     */
    public final boolean isSuccessful() {
        return this.successful;
    }
}
