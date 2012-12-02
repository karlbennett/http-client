package http.header;

import javax.activation.MimeType;

/**
 * Form {@code URL} encoded {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class FormUrlEncodedContentType extends ContentType {

    /**
     * The {@code MIME} type string for Form {@code URL} encoded content.
     */
    public static final String APPLICATION_X_WWW_FORM_URL_ENCODED = "application/x-www-form-urlencoded";

    /**
     * The {@link MimeType} object for Form {@code URL} encoded content.
     */
    public static final MimeType MIME_TYPE = stringToMimeType(APPLICATION_X_WWW_FORM_URL_ENCODED);

    /**
     * Create a new {@code FormUrlEncodedContentType}
     */
    public FormUrlEncodedContentType() {
        super(MIME_TYPE);
    }
}
