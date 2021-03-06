package networking_and_security;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLContext;

/**
 * This class contains a public static method for creating a {@link SSLContext}
 * object to be used in establishing a SSL encrypted network connection.
 *
 * @author iliyan-kostov
 */
public class SSLContextFactory {

    /**
     * Creates and returns a {@link SSLContext} object to be used in
     * establishing a SSL encrypted network connection, by using a pair of a
     * keystore file and a truststore file with their respective passwords, and
     * uses the provided {@link ExceptionHandler} object to pass possible
     * exceptions to for handling.
     *
     * @param keyStoreFile a keystore file containing public and private key
     * pairs. Has to be compatible with the default {@link KeyStore} type used
     * by the JRE (in particular one that is created by the Java Keytool located
     * in the "%JAVA_HOME%\bin" directory).
     *
     * @param keyStorePassword the password for the keystore file.
     *
     * @param trustStoreFile a truststore file containing public keys (trusted
     * certificates). Has to be compatible with the default {@link KeyStore}
     * type used by the JRE (in particular one that is created by the Java
     * Keytool located in the "%JAVA_HOME%\bin" directory). A truststore is
     * effectively a {@link KeyStore} that only contains public keys (imported
     * from certificates).
     *
     * @param trustStorePassword the password for the truststore file.
     *
     * @param exceptionHandler an object implementing the
     * {@link ExceptionHandler} interface, used to handle possible thrown
     * exceptions, for example to output a log or display a message to the user.
     *
     * @return a {@link SSLContext} object to be used in establishing a SSL
     * encrypted network connection, or null if the creation fails.
     */
    public static SSLContext getSSLContext(
            File keyStoreFile, String keyStorePassword,
            File trustStoreFile, String trustStorePassword,
            ExceptionHandler exceptionHandler) {

        boolean failed = false;

        //create KeyManager object array:
        KeyManager[] keymanagers = null;
        {
            char[] keystorepassword = keyStorePassword.toCharArray();
            InputStream istream = null;
            KeyStore keystore;
            KeyManagerFactory keymanagerfactory;
            try {
                istream = new BufferedInputStream(new FileInputStream(keyStoreFile));
                keystore = KeyStore.getInstance(KeyStore.getDefaultType());
                keystore.load(istream, keystorepassword);
                keymanagerfactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keymanagerfactory.init(keystore, keystorepassword);
                keymanagers = keymanagerfactory.getKeyManagers();
            } catch (FileNotFoundException ex) {
                exceptionHandler.handle(ex);
                failed = true;
            } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException | IOException ex) {
                exceptionHandler.handle(ex);
                failed = true;
            } finally {
                if (istream != null) {
                    try {
                        istream.close();
                    } catch (IOException ex) {
                        exceptionHandler.handle(ex);
                    }
                }
            }
        }
        if (failed) {
            return null;
        }

        //create TrustManager object array:
        TrustManager[] trustmanagers = null;
        {
            char[] truststorepassword = trustStorePassword.toCharArray();
            InputStream istream = null;
            KeyStore truststore;
            TrustManagerFactory trustmanagerfactory;
            try {
                istream = new BufferedInputStream(new FileInputStream(trustStoreFile));
                truststore = KeyStore.getInstance(KeyStore.getDefaultType());
                truststore.load(istream, truststorepassword);
                trustmanagerfactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustmanagerfactory.init(truststore);
                trustmanagers = trustmanagerfactory.getTrustManagers();
            } catch (FileNotFoundException ex) {
                exceptionHandler.handle(ex);
                failed = true;
            } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException ex) {
                exceptionHandler.handle(ex);
                failed = true;
            } finally {
                if (istream != null) {
                    try {
                        istream.close();
                    } catch (IOException ex) {
                        exceptionHandler.handle(ex);
                    }
                }
            }
        }
        if (failed) {
            return null;
        }

        //create SecureRandom object:
        SecureRandom random = new SecureRandom();

        //create SSLContext:
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("SSL");
            sslcontext.init(keymanagers, trustmanagers, random);
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            exceptionHandler.handle(ex);
            failed = true;
        }
        if (failed) {
            return null;
        }

        return sslcontext;
    }
}
