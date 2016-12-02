package networking_and_security;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import javax.net.ssl.SSLContext;

/**
 * THIS CLASS IS INCOMPLETE! - TO BE ADDED: LISTENING THREAD AND TASK MANAGER!
 *
 * This class is a factory for creating a multi-threaded server that uses SSL
 * encryption. The server uses a single thread for listening on the server port
 * and accepting incoming client connections. The server creates and manages
 * additional threads to handle each authenticated client's incoming
 * communication (one thread per client). The communication is structured as a
 * flow of messages. The processing of the incoming messages is assigned to a
 * single server-side {@link MessageProcessor} object that acts as a message
 * handler. It operates on a single shared task list based on the order that the
 * messages were received by the server. The server execution is started by the
 * {@link #start(int)} method. (to be added)
 *
 * @author iliyan-kostov
 */
public class SSLServer {

    /**
     * whether the server has been successfully started by the
     * {@link #start(int)} method.
     */
    private boolean isStarted;

    /**
     * the {@link SSLContext} that the server will use for network communication
     * security.
     */
    private SSLContext sslContext;

    /**
     * the {@link ServerSocket} that will be used for accepting incoming client
     * connections. The socket will support and use SSL encryption.
     */
    private ServerSocket serverSocket;

    /**
     * the local port assigned for the server to use.
     */
    private int port;

    /**
     * the {@link ExceptionHandler} object that the server will use to output
     * logs or display messages.
     */
    private ExceptionHandler exceptionHandler;

    /**
     * processes client messages.
     */
    private MessageProcessor clientMessageProcessor;

    /**
     * processes messages from the database management system module.
     */
    private MessageProcessor databaseMessageProcessor;

    /**
     * @Deprecated
     *
     * @deprecated Use the
     * {@link #getSSLServer(File, String, File, String, ExceptionHandler, int, MessageProcessor, MessageProcessor)}
     * method instead!
     *
     * Initializes the {@link SSLServer} object fields according to the
     * arguments provided, but does not start the server. To start the server
     * the {@link #start(int)} function should be called. This allows port
     * changing even if the {@link SSLServer} object is created but the server
     * is not yet operative.
     *
     * @param sslContext the {@link SSLContext} for the server that describes
     * the server's private and public key pairs, the encryption mechanisms, the
     * trusted public keys of clients (certificates) and other
     * encryption-related features.
     *
     * @param port the local port assigned for the server to use (the port to
     * create the {@link ServerSocket}.
     *
     * @param exceptionHandler an object implementing the
     * {@link ExceptionHandler} interface, used to handle possible thrown
     * exceptions, for example to output a log or display a message to the user.
     *
     * @param clientMessageProcessor the processor for messages from the client.
     *
     * @param databaseMessageProcessor the processor for messages from the
     * database management system module.
     */
    public SSLServer(SSLContext sslContext, int port, ExceptionHandler exceptionHandler, MessageProcessor clientMessageProcessor, MessageProcessor databaseMessageProcessor) {
        this.sslContext = sslContext;
        this.port = port;
        this.exceptionHandler = exceptionHandler;
        this.serverSocket = null;
        this.clientMessageProcessor = clientMessageProcessor;
        this.databaseMessageProcessor = databaseMessageProcessor;
    }

    /**
     * Private constructor, not intended for use outside of the class.
     */
    private SSLServer() {
        this.isStarted = false;
        this.sslContext = null;
        this.port = -1;
        this.exceptionHandler = null;
        this.serverSocket = null;
        this.clientMessageProcessor = null;
        this.databaseMessageProcessor = null;
    }

    /**
     * Initializes and returns a {@link SSLServer} object fields according to
     * the arguments provided, but does not start the server. This method uses
     * the
     * {@link SSLContextFactory#getSSLContext(File, String, File, String, ExceptionHandler)}
     * method to create a {@link SSLContext} for the server. To start the server
     * the {@link #start(int)} method should be called. This allows port
     * changing even if the {@link SSLServer} object is created but the server
     * is not yet operative.
     *
     * @param keyStoreFile keystore file.
     *
     * @param keyStorePassword keystore file password.
     *
     * @param trustStoreFile truststore file.
     *
     * @param trustStorePassword truststore file password.
     *
     * @param exceptionHandler an object implementing the
     * {@link ExceptionHandler} interface, used to handle possible thrown
     * exceptions, for example to output a log or display a message to the user.
     *
     * @param port the local port assigned for the server to use (the port to
     * create the {@link ServerSocket}.
     *
     * @param clientMessageProcessor the processor for messages from the client.
     *
     * @param databaseMessageProcessor the processor for messages from the
     * database management system module.
     *
     * @return a {@link SSLServer} object if successful, otherwise null.
     *
     * @see
     * {@link SSLContextFactory#getSSLContext(File, String, File, String, ExceptionHandler)}
     */
    public static SSLServer getSSLServer(
            File keyStoreFile, String keyStorePassword,
            File trustStoreFile, String trustStorePassword,
            ExceptionHandler exceptionHandler,
            int port,
            MessageProcessor clientMessageProcessor,
            MessageProcessor databaseMessageProcessor) {

        boolean failed = true;

        SSLServer result = new SSLServer();

        result.sslContext = SSLContextFactory.getSSLContext(keyStoreFile, keyStorePassword, trustStoreFile, trustStorePassword, exceptionHandler);
        if (result.sslContext == null) {
            failed = true;
        }
        if (failed) {
            return null;
        }

        result.port = port;
        result.exceptionHandler = exceptionHandler;
        result.clientMessageProcessor = clientMessageProcessor;
        result.databaseMessageProcessor = databaseMessageProcessor;

        result.serverSocket = null; // created by the {@link #start(int)} method
        result.isStarted = false;  // set by the {@link #start(int)} method

        if (!failed) {
            return result;
        } else {
            return null;
        }
    }

    /**
     * Starts the server by creating a {@link SSLServerSocket} on the specified
     * port, using a specified {@link SSLContext}. Exceptions are passed to the
     * {@link ExceptionHandler} object.
     *
     * @param port the local port assigned for the server to use (the port to
     * create the {@link ServerSocket}.
     *
     * @return true if successful, false otherwise. If the server has already
     * been started, returns false.
     */
    public boolean start(int port) {
        boolean successful = true;

        if (this.isStarted) {
            successful = false;
            this.exceptionHandler.handle(new IllegalStateException("Trying to start an already started server!"));
            return successful;
        } else {
            //create the server socket:
            try {
                this.serverSocket = this.sslContext.getServerSocketFactory().createServerSocket(this.port);
            } catch (java.lang.IllegalStateException | IOException ex) {
                successful = false;
                this.exceptionHandler.handle(ex);
            }
            if (!successful) {
                return false;
            }

            //initializes the task manager: TBA
            //start the thread that listens for incoming client connection requests and authenticates clients: TBA
            //start the thread for the client message processor: TBA
            //start the thread for the database message processor: TBA
            return successful;
        }
    }

    /**
     * Returns the local port assigned for the server to use.
     *
     * @return
     */
    public final int getPort() {
        return this.port;
    }
}
