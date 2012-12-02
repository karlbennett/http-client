package http.header;

import javax.activation.MimeType;

/**
 * @author Karl Bennett
 */
public class XmlContentType extends ContentType {

    public static final String APPLICATION_XML = "application/xml";

    public static final MimeType MIME_TYPE = stringToMimeType(APPLICATION_XML);

    public XmlContentType() {
        super(MIME_TYPE);
    }
}
