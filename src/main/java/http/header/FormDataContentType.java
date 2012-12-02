package http.header;

import javax.activation.MimeType;

/**
 * Form data {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class FormDataContentType extends ContentType {

    /**
     * The {@code MIME} type string for Form data encoded content.
     */
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    /**
     * The {@link javax.activation.MimeType} object for Form data encoded content.
     */
    public static final MimeType MIME_TYPE = stringToMimeType(MULTIPART_FORM_DATA);

    /**
     * Create a new {@code FormDataContentType}
     */
    public FormDataContentType() {
        super(MIME_TYPE);
    }
}
