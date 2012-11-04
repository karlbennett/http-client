package http;

/**
 * An exception that is thrown when a malformed URL is encountered. This is thrown instead of the
 * {@link java.net.MalformedURLException} and {@link java.net.URISyntaxException} so that the {@link java.net.URL} can
 * be instantiated without the need for checked exceptions.
 *
 * @author Karl Bennett
 */
public class URLException extends IllegalArgumentException {

    /**
     * Create an empty {@code URLException}.
     */
    public URLException() {
    }

    /**
     * Create a {@code URLException} with the supplied message.
     *
     * @param message the message for the exception.
     */
    public URLException(String message) {
        super(message);
    }

    /**
     * Create a {@code URLException} with the supplied message and cause.
     *
     * @param message the message for the exception.
     * @param cause   the cause of the exception, this should be either a {@link java.net.MalformedURLException} or
     *                {@link java.net.URISyntaxException}.
     */
    public URLException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a {@code URLException} with the supplied cause.
     *
     * @param cause   the cause of the exception, this should be either a {@link java.net.MalformedURLException} or
     *                {@link java.net.URISyntaxException}.
     */
    public URLException(Throwable cause) {
        super(cause);
    }
}
