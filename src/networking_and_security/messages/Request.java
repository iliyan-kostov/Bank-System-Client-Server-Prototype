package networking_and_security.messages;

/**
 * This is an abstract class that represents a request {@link Message}.
 *
 * @author iliyan-kostov
 */
public abstract class Request extends Message {

    /**
     * Constructs a {@link Request} using the provided arguments. Requests
     * require a response so the superclass constructor call always sets that to
     * true.
     *
     * @param type the type of the message.
     */
    public Request(TYPE type) {
        super(type, true);
        this.requiresResponse();
    }
}
