package http;

/**
 * An exception that is thrown when a malformed URI is encountered. This is thrown instead of the
 * {@link java.net.MalformedURLException} and {@link java.net.URISyntaxException} so that the {@link java.net.URI} can
 * be instantiated without the need for checked exceptions.
 *
 * @author Karl Bennett
 */
public class URIException extends IllegalArgumentException {

    /**
     * Create an empty {@code URIException}.
     */
    public URIException() {
    }

    /**
     * Create a {@code URIException} with the supplied message.
     *
     * @param message the message for the exception.
     */
    public URIException(String message) {
        super(message);
    }

    /**
     * Create a {@code URIException} with the supplied message and cause.
     *
     * @param message the message for the exception.
     * @param cause   the cause of the exception, this should be either a {@link java.net.MalformedURLException} or
     *                {@link java.net.URISyntaxException}.
     */
    public URIException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a {@code URIException} with the supplied cause.
     *
     * @param cause   the cause of the exception, this should be either a {@link java.net.MalformedURLException} or
     *                {@link java.net.URISyntaxException}.
     */
    public URIException(Throwable cause) {
        super(cause);
    }
}
