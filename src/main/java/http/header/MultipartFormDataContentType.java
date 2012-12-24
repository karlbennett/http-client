package http.header;

import javax.activation.MimeType;

import static http.util.MimeTypes.*;

/**
 * Form data {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class MultipartFormDataContentType extends ContentType {

    /**
     * The {@link javax.activation.MimeType} object for Form data encoded content.
     */
    public static final MimeType MIME_TYPE = quietMimeType(MULTIPART, FORM_DATA);

    /**
     * Create a new {@code MultipartFormDataContentType}
     */
    public MultipartFormDataContentType() {
        super(MIME_TYPE);
    }
}
