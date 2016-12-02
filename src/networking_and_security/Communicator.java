package networking_and_security;

import com.sun.corba.se.impl.protocol.giopmsgheaders.MessageHandler;
import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking_and_security.messages.Message;

/**
 * This class is a template for a {@link Thread} that manages a connection over
 * a {@link Socket}. The class provides a factory for creating
 * {@link Communicator} objects. The connection protocol is based on the
 * exchange and processing of {@link Message} objects.
 *
 * @author iliyan-kostov
 */
public class Communicator implements Runnable {

    /**
     * A flag whether to keep executing the {@link Thread}.
     */
    private boolean keepRunning;

    /**
     * The {@link Socket} which {@link InputStream} and {@link OutputStream}
     * will be used to construct
     */
    private Socket socket;

    /**
     * The {@link ExceptionHandler} object that the server will use to output
     * logs or display messages.
     */
    private ExceptionHandler exceptionHandler;

    /**
     * The {@link ObjectInputStream} used to receive incoming {@link Message}s.
     */
    private ObjectInputStream inputStream;

    /**
     * The {@link ObjectOutputStream} used to send outgoing {@link Message}s.
     */
    private ObjectOutputStream outputStream;

    /**
     * processes messages from {@link #inputStream}.
     */
    private MessageProcessor messageProcessor;

    /**
     * Private constructor, not intended for use outside of the class.
     */
    private Communicator() {
        this.socket = null;
        this.inputStream = null;
        this.outputStream = null;
        this.keepRunning = false;
    }

    /**
     * Creates and returns a {@link Communicator} object according to the
     * arguments provided.
     *
     * @param socket the {@link Socket} which {@link InputStream} and
     * {@link OutputStream} will be used to construct
     *
     * @param exceptionHandler the {@link ExceptionHandler} object that the
     * server will use to output logs or display messages.
     *
     * @return a {@link Communicator} object if successful, otherwise null.
     */
    public static Communicator getCommunicator(Socket socket, ExceptionHandler exceptionHandler) {
        Communicator result = new Communicator();
        boolean failed = false;

        result.socket = socket;
        try {
            result.inputStream = new ObjectInputStream(socket.getInputStream());
            result.outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            failed = true;
            exceptionHandler.handle(ex);
        }

        if (failed) {
            result.socket = null;
            try {
                result.inputStream.close();
            } catch (IOException ex) {
                exceptionHandler.handle(ex);
            }
            try {
                result.outputStream.close();
            } catch (IOException ex) {
                exceptionHandler.handle(ex);
            }
            return null;
        } else {
            return result;
        }
    }

    @Override
    public void run() {
        this.keepRunning = true;
        while (keepRunning) {
            try {
                Message incomingMessage = (Message) this.inputStream.readObject();
                Message result = messageProcessor.process(incomingMessage);
                if ((incomingMessage.requiresResponse()) && (result != null)) {
                    this.outputStream.writeObject(incomingMessage);
                }
            } catch (IOException | ClassNotFoundException ex) {
                exceptionHandler.handle(ex);
            }
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
