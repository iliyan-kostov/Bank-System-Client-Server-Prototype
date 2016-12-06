package multithreading;

import multithreading.messages.Message;
import java.io.Serializable;

/**
 *
 * @author iliyan-kostov
 */
public class Task implements Serializable {

    private Client client;
    private Message message;

    public Task(Client client, Message message) {
        this.client = client;
        this.message = message;
    }

    public Client getClient() {
        return this.client;
    }

    public Message getMessage() {
        return this.message;
    }
}
