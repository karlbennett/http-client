package http.header;

import static http.util.MimeTypes.APPLICATION_X_WWW_FORM_URL_ENCODED;

/**
 * Form {@code URL} encoded {@code Accept} header.
 *
 * @author Karl Bennett
 */
public class FormUrlEncodedAccept extends Accept {

    /**
     * Create a new {@code FormUrlEncodedAccept}
     */
    public FormUrlEncodedAccept() {
        super(APPLICATION_X_WWW_FORM_URL_ENCODED);
    }
}
