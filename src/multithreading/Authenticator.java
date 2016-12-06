package multithreading;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import multithreading.messages.Message;

/**
 *
 * @author iliyan-kostov
 */
public class Authenticator extends Thread {

    private Server server;
    private ServerSocket serverSocket;
    private ConnectionManager connectionManager;
    private boolean keepRunning;

    public Authenticator(Server server, ServerSocket serverSocket, ConnectionManager connectionManager) {
        this.server = server;
        this.serverSocket = serverSocket;
        this.connectionManager = connectionManager;
        this.keepRunning = true;
    }

    @Override
    public void run() {
        this.keepRunning = true;

        while (!this.isInterrupted() && this.keepRunning) {
            boolean successfulAuthentication = true;
            Socket socket = null;
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            Message authMessage = null;
            Client client = null;
            try {
                socket = this.serverSocket.accept();
                input = new ObjectInputStream(socket.getInputStream());
                output = new ObjectOutputStream(socket.getOutputStream());
                authMessage = (Message) input.readObject();
                client = (Client) this.server.authenticateClient(authMessage);
                if (client == null) {
                    successfulAuthentication = false;
                }
            } catch (Exception ex) {
                successfulAuthentication = false;
            } finally {
                if (successfulAuthentication) {
                    this.connectionManager.createConnection(client, socket, input, output);
                } else if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception ex1) {
                    }
                }
            }
        }
    }
}
