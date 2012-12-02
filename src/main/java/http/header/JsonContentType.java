package http.header;

import javax.activation.MimeType;

/**
 * {@code JSON} {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class JsonContentType extends ContentType {

    /**
     * The {@code MIME} type string for {@code JSON} content.
     */
    public static final String APPLICATION_JSON = "application/json";

    /**
     * The {@link MimeType} object for {@code JSON} content.
     */
    public static final MimeType MIME_TYPE = stringToMimeType(APPLICATION_JSON);

    /**
     * Create a new {@code JsonContentType}
     */
    public JsonContentType() {
        super(MIME_TYPE);
    }
}
