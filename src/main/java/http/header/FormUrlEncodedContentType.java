package http.header;

import javax.activation.MimeType;

/**
 * @author Karl Bennett
 */
public class FormUrlEncodedContentType extends ContentType {

    public static final String APPLICATION_X_WWW_FORM_URL_ENCODED = "application/x-www-form-urlencoded";

    public static final MimeType MIME_TYPE = stringToMimeType(APPLICATION_X_WWW_FORM_URL_ENCODED);

    public FormUrlEncodedContentType() {
        super(MIME_TYPE);
    }
}
