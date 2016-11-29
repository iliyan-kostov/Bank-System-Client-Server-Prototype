package networking_and_security.messages;

/**
 * A {@link Register_Request} is sent by a client that has not yet been
 * registered in the system and attempts to connect to the server for the first
 * time in order to register a new user account. Personal data has to be
 * submitted by the client in order to complete the registration.
 *
 * @author iliyan-kostov
 */
public class Register_Request extends Message {

    /**
     * the username requested by the user.
     */
    protected String username;
    /**
     * the password requested by the user.
     */
    protected String password;

    /**
     * the client first name provided by the user.
     */
    protected String firstName;

    /**
     * the client last name provided by the user.
     */
    protected String lastName;

    /**
     * the client phone number provided by the user.
     */
    protected String phoneNumber;

    /**
     * the client address provided the user.
     */
    protected String address;

    /**
     * Creates a {@link Register_Request} according to the arguments provided.
     *
     * @param username the username requested by the client.
     *
     * @param password the password requested by the client.
     *
     * @param firstName the client first name provided by the user.
     *
     * @param lastName the client last name provided by the user.
     *
     * @param phoneNumber the client phone number provided by the user.
     *
     * @param address the client address provided by the user.
     */
    public Register_Request(String username, String password, String firstName, String lastName, String phoneNumber, String address) {
        super(Type.AUTHENTICATION_REGISTER_REQUEST, true);
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * returns the client username requested by the user.
     *
     * @return the client username requested by the user.
     */
    public final String getUsername() {
        return this.username;
    }

    /**
     * returns the client password requested by the user.
     *
     * @return the client password requested by the user.
     */
    public final String getPassword() {
        return this.password;
    }

    /**
     * returns the client first name provided by the user.
     *
     * @return the client first name provided by the user.
     */
    public final String getFirstName() {
        return this.firstName;
    }

    /**
     * returns the client last name provided by the user.
     *
     * @return the client last name provided by the user.
     */
    public final String getLastName() {
        return this.lastName;
    }

    /**
     * returns the client phone number provided by the user.
     *
     * @return the client phone number provided by the user.
     */
    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * returns the client address provided by the user.
     *
     * @return the client address provided by the user.
     */
    public final String getAddress() {
        return this.address;
    }
}
