package multithreading;

import java.io.IOException;
import multithreading.messages.Message;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public Connection(Client client, Socket socket, Server server, ConnectionManager connectionManager) {
        this.client = client;
        this.socket = socket;
        this.server = server;
        this.connectionManager = connectionManager;
        this.inputStream = null;
        this.outputStream = null;
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

        // initialization:
        if (this.keepRunning) {
            try {
                this.inputStream = new ObjectInputStream(this.socket.getInputStream());
                this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            } catch (IOException ex) {
                this.keepRunning = false;
            }
        }

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
