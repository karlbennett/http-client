package http.header;

import javax.activation.MimeType;

/**
 * @author Karl Bennett
 */
public class JsonContentType extends ContentType {

    public static final String APPLICATION_JSON = "application/json";

    public static final MimeType MIME_TYPE = stringToMimeType(APPLICATION_JSON);

    public JsonContentType() {
        super(MIME_TYPE);
    }
}
