package http.header;

import javax.activation.MimeType;

import static http.util.MimeTypes.*;

/**
 * {@code JSON} {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class JsonContentType extends ContentType {

    /**
     * The {@link MimeType} object for {@code JSON} content.
     */
    public static final MimeType MIME_TYPE = quietMimeType(APPLICATION, JSON);

    /**
     * Create a new {@code JsonContentType}
     */
    public JsonContentType() {
        super(MIME_TYPE);
    }
}
