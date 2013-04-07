package http.header;

import javax.activation.MimeType;

import static http.header.Accept.ACCEPT;
import static http.util.MimeTypes.APPLICATION_JSON;

/**
 * @author Karl Bennett
 */
public class AcceptTest extends AbstractMimeTypeTest<Accept> {

    public AcceptTest() {
        super(ACCEPT, APPLICATION_JSON);
    }

    @Override
    protected Accept createMimeTypeHeader(MimeType mimeType) {

        return new Accept(mimeType);
    }

    @Override
    protected Accept createMimeTypeHeader(String primary, String sub) {

        return new Accept(primary, sub);
    }

    @Override
    protected Accept createMimeTypeHeader(String rawData) {

        return new Accept(rawData);
    }

    @Override
    protected Accept copyMimeTypeHeader(Accept accept) {

        return new Accept(accept);
    }

    @Override
    protected Accept convertMimeTypeHeader(Header header) {

        return new Accept(header);
    }
}
