package http.header;

import static http.util.MimeTypes.MULTIPART_FORM_DATA;

/**
 * Form data {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class MultipartFormDataContentType extends ContentType {

    /**
     * Create a new {@code MultipartFormDataContentType}
     */
    public MultipartFormDataContentType() {
        super(MULTIPART_FORM_DATA);
    }
}
