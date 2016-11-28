package networking_and_security;

import java.io.IOException;
import java.net.ServerSocket;
import javax.net.ssl.SSLContext;

/**
 * THIS CLASS IS INCOMPLETE! - TO BE ADDED: LISTENING THREAD AND TASK MANAGER!
 *
 * This class is a factory for creating a multi-threaded server that uses SSL
 * encryption. The server uses a single thread for listening on the server port
 * and accepting incoming client connections. The server manages additional
 * threads to handle each authenticated client's incoming communication (one
 * thread per client). The communication is structured as a flow of messages.
 * The processing of the incoming messages is assigned to a single server-side
 * message handler that operates on a single shared task list. (to be added)
 *
 * @author iliyan-kostov
 */
public class SSLServer {

    /**
     * the {@link SSLContext} that the server will use for network communication
     * security.
     */
    protected SSLContext sslContext;

    /**
     * the {@link ServerSocket} that will be used for accepting incoming client
     * connections. The socket will support and use SSL encryption.
     */
    protected ServerSocket serverSocket;

    /**
     * the local port that the server will operate on.
     */
    protected int port;

    /**
     * the {@link ExceptionHandler} object that the server will use to output
     * logs or display messages.
     */
    protected ExceptionHandler exceptionHandler;

    /**
     * Initializes the {@link SSLServer} object fields according to the
     * arguments provided, but does not start the server. To start the server
     * the {@link #start()} function should be called. This allows port changing
     * even if the {@link SSLServer} object is created but the server is not yet
     * operative.
     *
     * @param sslContext the {@link SSLContext} for the server that describes
     * the server's private and public key pairs, the encryption mechanisms, the
     * trusted public keys of clients (certificates) and other
     * encryption-related features.
     *
     * @param port the local port that will be used to start the server on (the
     * port to create the {@link ServerSocket}.
     *
     * @param exceptionHandler an object implementing the
     * {@link ExceptionHandler} interface, used to handle possible thrown
     * exceptions, for example to output a log or display a message to the user.
     */
    public SSLServer(SSLContext sslContext, int port, ExceptionHandler exceptionHandler) {
        this.sslContext = sslContext;
        this.port = port;
        this.exceptionHandler = exceptionHandler;
        this.serverSocket = null;
    }

    /**
     * Starts the server by creating a {@link SSLServerSocket} on the specified
     * port, using a specified {@link SSLContext}. Exceptions are passed to the
     * {@link ExceptionHandler} object.
     *
     * @return true if successful, false otherwise.
     */
    public boolean start() {
        boolean successful = true;

        //create the server socket:
        try {
            this.serverSocket = this.sslContext.getServerSocketFactory().createServerSocket(this.port);
        } catch (IOException ex) {
            successful = false;
            this.exceptionHandler.handle(ex);
        }
        if (!successful) {
            return false;
        }

        //initializes the task manager: TBA
        //start the thread that listens for incoming client connection requests: TBA
        return successful;
    }
}
