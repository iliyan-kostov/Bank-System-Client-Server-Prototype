package multithreading;

import java.io.IOException;
import multithreading.messages.Message;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author iliyan-kostov
 */
public class Connection extends Thread {

    private Client client;
    private Server server;
    private ConnectionManager connectionManager;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private boolean keepRunning;

    public Connection(Client client, Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream, Server server, ConnectionManager connectionManager) {
        this.client = client;
        this.socket = socket;
        this.server = server;
        this.connectionManager = connectionManager;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.keepRunning = true;
    }

    public synchronized boolean closeSocket() {
        try {
            this.socket.close();
        } catch (IOException ex) {
        }
        this.keepRunning = false;
        return true;
    }

    @Override
    public void run() {
        this.keepRunning = true;

        // initialization: at constructor
        // operation:
        while (!this.isInterrupted() && this.keepRunning) {
            try {
                // read input message:
                Message input = (Message) inputStream.readObject();

                // process input message:
                Message result = this.server.processTask(new Task(this.client, input));

                // return the result to the client:
                if (result != null) {
                    this.outputStream.writeObject(result);
                }
            } catch (Exception ex) {
                this.keepRunning = false;
            }
        }

        // cleanup:
        this.connectionManager.stopConnection(this.client);
    }
}
