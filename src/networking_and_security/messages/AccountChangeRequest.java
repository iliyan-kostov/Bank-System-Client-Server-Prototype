package networking_and_security.messages;

/**
 * This is an abstract class that represents a {@link Request} for changing
 * account data.
 *
 * @author iliyan-kostov
 */
public abstract class AccountChangeRequest extends Request {

    /**
     * Constructs a {@link AccountChangeRequest} using the provided arguments.
     *
     * @param type the type of the message.
     */
    public AccountChangeRequest(TYPE type) {
        super(type);
    }
}
