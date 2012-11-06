package http.date;

/**
 * An exception that is thrown when a date has failed to be parsed. This is used instead of the {@link java.text.ParseException} so that a date can be
 *
 * @author Karl Bennett
 */
public class DateParseException extends IllegalArgumentException {

    /**
     * Create an empty {@code DateParseException}.
     */
    public DateParseException() {
    }

    /**
     * Create an empty {@code DateParseException} with the supplied message.
     *
     * @param message the message for the exception.
     */
    public DateParseException(String message) {
        super(message);
    }

    /**
     * Create a {@code DateParseException} with the supplied message and cause.
     *
     * @param message the message for the exception.
     * @param cause   the cause of the exception, this should be a {@link java.text.ParseException}.
     */
    public DateParseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a {@code DateParseException} with the supplied cause.
     *
     * @param cause the cause of the exception, this should be a {@link java.text.ParseException}.
     */
    public DateParseException(Throwable cause) {
        super(cause);
    }
}
