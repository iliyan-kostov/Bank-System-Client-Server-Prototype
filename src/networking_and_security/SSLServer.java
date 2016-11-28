package networking_and_security;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;

/**
 * THIS CLASS IS INCOMPLETE!
 *
 * This class is a factory for creating a multi-threaded SSL encrypted server
 * that uses a single thread for listening on the server port and accepting
 * incoming client connections. The server manages additional threads to handle
 * each authenticated client's incoming communication (one thread per client).
 * The communication is structured as a flow of messages. The processing of the
 * incoming messages is assigned to a single server-side message handler that
 * operates on a single shared task list. (to be added)
 *
 * @author iliyan-kostov
 */
public class SSLServer {

    /**
     * The socket that is used for accepting incoming client connections.
     */
    public SSLServerSocket sslServerSocket;

    public SSLServer() {
        this.sslServerSocket = null;
    }

    /**
     * Returns the {@link SSLServer} object by creating a
     * {@link SSLServerSocket} on the specified port, using a specified
     * {@link SSLContext}. Exceptions are passed to the {@link ExceptionHandler}
     * object.
     *
     * @param sslContext the {@link SSLContext} for the server that describes
     * the server's private and public key pairs, the encryption mechanisms, the
     * trusted public keys of clients (certificates) and other
     * encryption-related features.
     *
     * @param port the port that will be used to start the server on (the port
     * to create the {@link SSLServerSocket}.
     *
     * @param exceptionHandler an object implementing the
     * {@link ExceptionHandler} interface, used to handle possible thrown
     * exceptions, for example to output a log or display a message to the user.
     *
     * @return
     */
    public static SSLServer startServer(SSLContext sslContext, int port, ExceptionHandler exceptionHandler) {
        SSLServer result = new SSLServer();
        try {
            result.sslServerSocket = (SSLServerSocket) (sslContext.getServerSocketFactory().createServerSocket(port));
        } catch (IOException ex) {
            Logger.getLogger(SSLServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
