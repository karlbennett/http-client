package http.header;

import javax.activation.MimeType;

import static http.header.ContentType.CONTENT_TYPE;
import static http.util.MimeTypes.APPLICATION_JSON;

/**
 * @author Karl Bennett
 */
public class ContentTypeTest extends AbstractMimeTypeTest<ContentType>{

    public ContentTypeTest() {
        super(CONTENT_TYPE, APPLICATION_JSON);
    }

    @Override
    protected ContentType createMimeTypeHeader(MimeType mimeType) {

        return new ContentType(mimeType);
    }

    @Override
    protected ContentType createMimeTypeHeader(String primary, String sub) {

        return new ContentType(primary, sub);
    }

    @Override
    protected ContentType createMimeTypeHeader(String rawData) {

        return new ContentType(rawData);
    }

    @Override
    protected ContentType copyMimeTypeHeader(ContentType header) {

        return new ContentType(header);
    }

    @Override
    protected ContentType convertMimeTypeHeader(Header header) {

        return new ContentType(header);
    }
}
