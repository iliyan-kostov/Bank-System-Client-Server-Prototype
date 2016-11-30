package networking_and_security.messages;

/**
 * This is an abstract class that represents a response {@link Message}.
 *
 * @author iliyan-kostov
 */
public abstract class Response extends Message {

    /**
     * the description of the response, if necessary.
     */
    private String description;

    /**
     * Constructs a {@link Response} using the provided arguments. Responses
     * don't require a response so the superclass constructor call always sets
     * that to false.
     *
     * @param type the type of the message.
     *
     * @param description the description of the response, if necessary.
     */
    public Response(TYPE type, String description) {
        super(type, false);
        this.description = description;
    }
}
