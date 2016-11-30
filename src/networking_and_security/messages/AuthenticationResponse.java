package networking_and_security.messages;

/**
 * This is an abstract class that represents a {@link Response} to a
 * {@link AuthenticationRequest}.
 *
 * @author iliyan-kostov
 */
public abstract class AuthenticationResponse extends Response {

    /**
     * whether the requested action was successful.
     */
    private boolean isSuccessful;

    /**
     * Constructs a {@link AuthenticationResponse} using the provided arguments.
     *
     * @param type the type of the message.
     *
     * @param description the description of the response, if necessary.
     *
     * @param isSuccessful whether the requested action was successful.
     */
    public AuthenticationResponse(TYPE type, String description, boolean isSuccessful) {
        super(type, description);
        this.isSuccessful = isSuccessful;
    }

    /**
     * Returns whether the requested action was successful.
     *
     * @return whether the requested action was successful.
     */
    public boolean isSuccessful() {
        return this.isSuccessful;
    }
}
