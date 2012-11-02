package http;

/**
 * Represent the body of an HTTP message, be this a {@link Request} or {@link Response}. The type of content the body
 * contains can be set on instantiation.
 *
 * @param <T> the type of content the body contains e.g. {@code String}, {@code Collection&lt;Parameter&gt;},
 *            {@code InputStream}...
 * @author Karl Bennett
 */
public class Body<T> {

    /**
     * Create a new {@code Body} containing content of the set generic type.
     *
     * @param content the content of the body.
     */
    public Body(T content) {

    }

    /**
     * @return return the content of the body.
     */
    public T getContent() {

        return null;
    }

    /**
     * Return the content of the body as the supplied type. This uses the clients converters to convert the current
     * type of the body into the requested type.
     *
     * @param type the type that the body content should be converted into.
     * @param <T>  the generic type that the body content should be converted into.
     * @return the content of the body as the requested type.
     * @throws IllegalStateException if no converter could be found that supports the body content type as input and the
     *                               requested type as output.
     */
    public <T> T getContent(Class<T> type) {

        return null;
    }
}
