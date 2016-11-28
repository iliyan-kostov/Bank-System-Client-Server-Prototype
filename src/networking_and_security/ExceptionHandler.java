package networking_and_security;

/**
 * This interface specifies a contract for exception handling. It is intended to
 * be used as an addition to an application that requires exception handling of
 * some kind - for example outputting a log or displaying messages to the user.
 *
 * @author iliyan-kostov
 */
public interface ExceptionHandler {

    public void handle(Exception ex);
}
