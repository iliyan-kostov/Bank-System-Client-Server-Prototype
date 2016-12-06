package multithreading;

import multithreading.messages.Message;

/**
 *
 * @author iliyan-kostov
 */
public interface DatabaseController {

    /**
     * SYNCHRONIZED OR NOT - SYNCHRONIZATION IS DONE AT SERVER SIDE !!!
     */
    public Message processTask(Task task);

    /**
     * SYNCHRONIZED OR NOT - SYNCHRONIZATION IS DONE AT SERVER SIDE !!! RETURN
     * NULL IF FAILED !!!
     */
    public Client authenticateClient(Message message);
}
