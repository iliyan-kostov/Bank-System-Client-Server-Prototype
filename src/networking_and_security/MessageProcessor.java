package networking_and_security;

/**
 * This interface specifies a contract for processing various {@link Message}
 * objects. It is intended to be used as a handler in a module that relies on
 * communication via {@link Message} transfer. The result of processing a
 * {@link Message} can also be a {@link Message} so that the result could be
 * passed for further processing or sent over the network.
 *
 * @author iliyan-kostov
 */
public interface MessageProcessor {

    /**
     * Processes the incoming {@link Message} and returns a {@link Message} that
     * contains the results.
     *
     * @param message the incoming {@link Message}.
     *
     * @return the {@link Message} that describes the result.
     */
    public Message process(Message message);
}
