package multithreading;

import java.io.Serializable;

/**
 *
 * @author iliyan-kostov
 */
public class Client implements Serializable, Comparable<Client> {

    private String id;

    public Client(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public int compareTo(Client other) {
        return (this.id).compareTo(other.id);
    }
}
