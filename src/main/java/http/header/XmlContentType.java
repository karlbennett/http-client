package http.header;

import javax.activation.MimeType;

/**
 * {@code XML} {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class XmlContentType extends ContentType {

    /**
     * The {@code MIME} type string for {@code XML} content.
     */
    public static final String APPLICATION_XML = "application/xml";

    /**
     * The {@link MimeType} object for {@code XML} content.
     */
    public static final MimeType MIME_TYPE = stringToMimeType(APPLICATION_XML);


    /**
     * Create a new {@code XmlContentType}
     */
    public XmlContentType() {
        super(MIME_TYPE);
    }
}
