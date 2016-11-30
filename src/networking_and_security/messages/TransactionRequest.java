package networking_and_security.messages;

/**
 * This is an abstract class that represents a {@link Request} for a
 * transaction.
 *
 * @author iliyan-kostov
 */
public abstract class TransactionRequest extends Request {

    /**
     * Constructs a {@link TransactionRequest} using the provided arguments.
     *
     * @param type the type of the message.
     */
    public TransactionRequest(TYPE type) {
        super(type);
    }
}
