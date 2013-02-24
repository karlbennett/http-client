package http.header;

import static http.util.MimeTypes.MULTIPART_FORM_DATA;

/**
 * Form data {@code Accept} header.
 *
 * @author Karl Bennett
 */
public class MultipartFormDataAccept extends Accept {

    /**
     * Create a new {@code MultipartFormDataAccept}
     */
    public MultipartFormDataAccept() {
        super(MULTIPART_FORM_DATA);
    }
}
