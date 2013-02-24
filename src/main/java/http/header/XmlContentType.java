package http.header;

import static http.util.MimeTypes.APPLICATION_XML;

/**
 * {@code XML} {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class XmlContentType extends ContentType {

    /**
     * Create a new {@code XmlContentType}
     */
    public XmlContentType() {
        super(APPLICATION_XML);
    }
}
