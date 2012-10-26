package http;

/**
 * @author Karl Bennett
 */
public class URLException extends IllegalArgumentException {

    public URLException() {
    }

    public URLException(String s) {
        super(s);
    }

    public URLException(String message, Throwable cause) {
        super(message, cause);
    }

    public URLException(Throwable cause) {
        super(cause);
    }
}
