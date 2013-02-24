package http.header;

import static http.util.MimeTypes.APPLICATION_XML;

/**
 * {@code XML} {@code Accept} header.
 *
 * @author Karl Bennett
 */
public class XmlAccept extends Accept {

    /**
     * Create a new {@code XmlAccept}
     */
    public XmlAccept() {
        super(APPLICATION_XML);
    }
}
