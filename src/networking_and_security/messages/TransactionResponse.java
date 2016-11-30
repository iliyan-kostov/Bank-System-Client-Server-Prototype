package networking_and_security.messages;

/**
 * This is an abstract class that represents a {@link Response} to a
 * {@link TransactionRequest}.
 *
 * @author iliyan-kostov
 */
public abstract class TransactionResponse extends Response {

    /**
     * Constructs a {@link TransactionResponse} using the provided arguments.
     *
     * @param type the type of the message.
     *
     * @param description the description of the response, if necessary.
     */
    public TransactionResponse(TYPE type, String description) {
        super(type, description);
    }
}
