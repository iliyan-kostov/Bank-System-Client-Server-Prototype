package multithreading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import multithreading.messages.AuthenticationDenied;
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

        // operation:
        while (!this.isInterrupted() && this.keepRunning) {
            boolean successfulAuthentication = true;
            Socket incoming = null;
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            Message authMessage = null;
            Client client = null;
            try {
                incoming = this.serverSocket.accept();
                try {
                    input = new ObjectInputStream(incoming.getInputStream());
                    output = new ObjectOutputStream(incoming.getOutputStream());
                    authMessage = (Message) input.readObject();
                    client = this.server.authenticateClient(authMessage);
                    if (client == null) {
                        successfulAuthentication = false;
                    }
                } catch (Exception ex) {
                    successfulAuthentication = false;
                } finally {
                    if (!successfulAuthentication) {
                        // send failed message through output !!!
                        if (output != null) {
                            try {
                                output.writeObject(new AuthenticationDenied());
                            } catch (IOException ex) {
                            }
                        }
                        try {
                            incoming.close();
                        } catch (Exception ex1) {
                        }
                    }
                }
            } catch (IOException ex) {
                // exception when accepting incoming connections from the server socket
            }
        }

        // cleanup:
        try {
            this.serverSocket.close();
        } catch (IOException ex) {
        }
        this.keepRunning = false;
    }
}
