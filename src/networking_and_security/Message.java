package networking_and_security;

import java.io.Serializable;

/**
 * This class represents a the basic of networking communication in the system -
 * a message. The class is {@link Serializable} in order to allow
 * {@link Message} as well as all its derived classes by definition. This allows
 * the objects of the class or its derived classes to be sent over a network
 * connection or to be stored on a device. It is intended to define only a very
 * basic functionality and to be used exclusively as a base class for
 * extensions. Some messages (requests) require a response, while others
 * (responses) don't.
 *
 * @author iliyan-kostov
 */
public abstract class Message implements Serializable {

    /**
     * the type of the message, used to recognize the actions requested and also
     * to distinguish between different derived classes.
     */
    protected String type;

    /**
     * whether this {@link Message} requires a response or not.
     */
    protected boolean requiresResponse;

    /**
     * Constructs a message using the provided {@link String} as message type.
     *
     * @param type the type of the message.
     *
     * @param requiresResponse whether this {@link Message} requires a response
     * or not.
     */
    public Message(String type, boolean requiresResponse) {
        this.type = type;
        this.requiresResponse = requiresResponse;
    }

    /**
     * Returns the type of the message, used to recognize the actions requested
     * and also to distinguish between different derived classes.
     *
     * @return the type of the message.
     */
    public final String getType() {
        return this.type;
    }

    /**
     * Returns whether the {@link Message} requires a response.
     *
     * @return true if the {@link Message} requires a response, false otherwise.
     */
    public final boolean requiresResponse() {
        return this.requiresResponse;
    }
}
