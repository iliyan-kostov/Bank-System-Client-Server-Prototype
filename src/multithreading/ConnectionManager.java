package multithreading;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author iliyan-kostov
 */
public class ConnectionManager {

    private Server server;
    private TreeMap<Client, Connection> clientConnectionMap;
    private boolean keepRunning;

    public ConnectionManager(Server server) {
        this.server = server;
        this.clientConnectionMap = new TreeMap<>();
        this.keepRunning = true;
    }

    public synchronized boolean stopConnection(Client client) {
        if (client != null) {
            Connection connection = this.clientConnectionMap.get(client);
            if (connection != null) {
                // interrupt the active connection:
                connection.interrupt();
                // close the connection socket:
                connection.closeSocket();
                // remove map entry:
                clientConnectionMap.remove(client);
            }
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean createConnection(Client client, Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        if (this.keepRunning && client != null && socket != null) {
            // check if the client is already connected:
            {
                Connection test = this.clientConnectionMap.get(client);
                if (test != null) {
                    // close the active connection:
                    test.interrupt();
                    // remove map entry:
                    clientConnectionMap.remove(client);
                }
            }
            // create a new connection to the client:
            {
                Connection newConnection = new Connection(client, socket, this.server, this);
                clientConnectionMap.put(client, newConnection);
                newConnection.start();
            }
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean stopAll() {
        this.keepRunning = false;
        for (Map.Entry<Client, Connection> entry : clientConnectionMap.entrySet()) {
            Client client = entry.getKey();
            Connection connection = entry.getValue();
            if (connection != null) {
                // interrupt the active connection:
                connection.interrupt();
                // close the connection socket:
                connection.closeSocket();
                // remove map entry:
                clientConnectionMap.remove(client);
            }
        }
        return true;
    }
}
