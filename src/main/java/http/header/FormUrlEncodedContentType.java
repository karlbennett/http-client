package http.header;

import static http.util.MimeTypes.APPLICATION_X_WWW_FORM_URL_ENCODED;

/**
 * Form {@code URL} encoded {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class FormUrlEncodedContentType extends ContentType {

    /**
     * Create a new {@code FormUrlEncodedContentType}
     */
    public FormUrlEncodedContentType() {
        super(APPLICATION_X_WWW_FORM_URL_ENCODED);
    }
}
