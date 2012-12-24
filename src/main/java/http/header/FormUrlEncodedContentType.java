package http.header;

import javax.activation.MimeType;

import static http.util.MimeTypes.*;

/**
 * Form {@code URL} encoded {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class FormUrlEncodedContentType extends ContentType {

    /**
     * The {@link MimeType} object for Form {@code URL} encoded content.
     */
    public static final MimeType MIME_TYPE = quietMimeType(APPLICATION, X_WWW_FORM_URL_ENCODED);

    /**
     * Create a new {@code FormUrlEncodedContentType}
     */
    public FormUrlEncodedContentType() {
        super(MIME_TYPE);
    }
}
