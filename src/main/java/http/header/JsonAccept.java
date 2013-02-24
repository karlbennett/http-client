package http.header;

import static http.util.MimeTypes.APPLICATION_JSON;

/**
 * {@code JSON} {@code Accept} header.
 *
 * @author Karl Bennett
 */
public class JsonAccept extends Accept {

    /**
     * Create a new {@code JsonAccept}
     */
    public JsonAccept() {
        super(APPLICATION_JSON);
    }
}
