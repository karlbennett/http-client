package http.serialisation;

import java.io.InputStream;

import static http.util.MimeTypes.*;

/**
 * A serialiser for multipart form data. It can be used to serialise an object or {@link java.util.Map} into a multipart
 * request body that contains multiple parameters. Also a {@link String} or {@link InputStream} can be serialised into a
 * multipart request body with a single parameter.
 *
 * @author Karl Bennett
 */
public class MultipartFormDataSerialiser extends InputStreamSerialiser {

    protected MultipartFormDataSerialiser() {
        super(quietMimeType(MULTIPART, FORM_DATA));
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> InputStream serialise(T object) {

        return null;
    }
}
