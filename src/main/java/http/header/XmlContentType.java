package http.header;

import javax.activation.MimeType;

import static http.util.MimeTypes.*;

/**
 * {@code XML} {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class XmlContentType extends ContentType {

    /**
     * The {@link MimeType} object for {@code XML} content.
     */
    public static final MimeType MIME_TYPE = quietMimeType(APPLICATION, XML);


    /**
     * Create a new {@code XmlContentType}
     */
    public XmlContentType() {
        super(MIME_TYPE);
    }
}
