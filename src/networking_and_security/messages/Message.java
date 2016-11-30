package networking_and_security.messages;

import java.io.Serializable;

/**
 * This class represents a the basic of networking communication in the system -
 * a message. The class is {@link Serializable} in order to allow
 * {@link Message} as well as all its derived classes by definition. This allows
 * the objects of the class or its derived classes to be sent over a network
 * connection or to be stored on a device. It is intended to define only a very
 * basic functionality and to be used exclusively as a base class for
 * extensions. {@link Message} subclass tree includes mostly abstract classes.
 * Some messages ({@link Request}) require a response, while others
 * ({@link Response}) don't.
 *
 * @author iliyan-kostov
 */
public abstract class Message implements Serializable {

    /**
     * the type of the message, used to recognize the actions requested and also
     * to distinguish between different derived classes when using
     * serialization. Different message types are implemented according to the
     * operations supported by the bank.
     */
    public static enum TYPE {
        /**
         * client request for login attempt.
         */
        LOGIN_REQUEST,
        /**
         * server response for client login attempt.
         */
        LOGIN_RESPONSE,
        /**
         * client request for registration attempt.
         */
        SIGNUP_REQUEST,
        /**
         * server response for client registration attempt.
         */
        SIGNUP_RESPONSE
    };

    /**
     * the type of the message.
     */
    private TYPE type;

    /**
     * whether this {@link Message} requires a response or not.
     */
    private boolean requiresResponse;

    /**
     * Constructs a {@link Message} using the provided arguments.
     *
     * @param type the type of the message.
     *
     * @param requiresResponse whether this {@link Message} requires a response
     * or not.
     */
    public Message(TYPE type, boolean requiresResponse) {
        this.type = type;
        this.requiresResponse = requiresResponse;
    }

    /**
     * Returns the type of the message, used to recognize the actions requested
     * and also to distinguish between different derived classes when using
     * serialization.
     *
     * @return the type of the message.
     */
    public final TYPE getType() {
        return this.type;
    }

    /**
     * Returns whether the message requires a response.
     *
     * @return true if the message requires a response, false otherwise.
     */
    public final boolean requiresResponse() {
        return this.requiresResponse;
    }
}
