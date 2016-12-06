package multithreading;

import java.io.IOException;
import multithreading.messages.Message;
import java.net.ServerSocket;
import javax.net.ServerSocketFactory;

/**
 *
 * @author iliyan-kostov
 */
public class Server {

    private ServerSocketFactory serverSocketFactory;
    private int port;
    private DatabaseController databaseController;
    private ServerSocket serverSocket;
    private boolean keepRunning;
    private ConnectionManager connectionManager;
    private Authenticator authenticator;

    public Server(ServerSocketFactory serverSocketFactory, DatabaseController databaseController) {
        this.keepRunning = false;
        this.serverSocketFactory = serverSocketFactory;
        this.databaseController = databaseController;
    }

    /**
     * RETURN NULL IF FAILED !!!
     */
    public synchronized Message processTask(Task task) {
        if (this.keepRunning) {
            return this.databaseController.processTask(task);
        } else {
            return null;
        }
    }

    /**
     * RETURN NULL IF FAILED !!!
     */
    public synchronized Client authenticateClient(Message message) {
        if (this.keepRunning) {
            return this.databaseController.authenticateClient(message);
        } else {
            return null;
        }
    }

    public synchronized boolean startServer(int port) {
        if (!this.keepRunning) {
            this.port = port;
            this.keepRunning = true;
            try {
                this.serverSocket = this.serverSocketFactory.createServerSocket(this.port);
            } catch (IOException ex) {
                keepRunning = false;
            }
            if (keepRunning) {
                this.connectionManager = new ConnectionManager(this);
                this.authenticator = new Authenticator(this, this.serverSocket, this.connectionManager);
                this.authenticator.start();
            }
            return false;
        } else {
            return false;
        }
    }

    public synchronized boolean stopServer() {
        if (this.keepRunning) {
            this.authenticator.interrupt();
            try {
                this.authenticator.join();
            } catch (InterruptedException ex) {
            }
            this.connectionManager.stopAll();
            try {
                this.serverSocket.close();
            } catch (IOException ex) {
            }
            this.keepRunning = false;
            return true;
        } else {
            return false;
        }
    }
}
