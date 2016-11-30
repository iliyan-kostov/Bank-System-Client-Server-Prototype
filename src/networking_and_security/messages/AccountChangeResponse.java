package networking_and_security.messages;

/**
 * This is an abstract class that represents a {@link Response} to a
 * {@link AccountChangeRequest}.
 *
 * @author iliyan-kostov
 */
public abstract class AccountChangeResponse extends Response {

    /**
     * Constructs a {@link AccountChangeResponse} using the provided arguments.
     *
     * @param type the type of the message.
     *
     * @param description the description of the response, if necessary.
     */
    public AccountChangeResponse(TYPE type, String description) {
        super(type, description);
    }
}
