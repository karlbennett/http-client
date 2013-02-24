package http.header;

import static http.util.MimeTypes.APPLICATION_JSON;

/**
 * {@code JSON} {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class JsonContentType extends ContentType {

    /**
     * Create a new {@code JsonContentType}
     */
    public JsonContentType() {
        super(APPLICATION_JSON);
    }
}
