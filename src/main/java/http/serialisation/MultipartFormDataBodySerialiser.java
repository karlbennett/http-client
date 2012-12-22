package http.serialisation;

import http.header.MultipartFormDataContentType;

import java.io.InputStream;

/**
 * A serialiser for multipart form data. It can be used to serialise an object or {@link java.util.Map} into a multipart
 * request body that contains multiple parameters. Also a {@link String} or {@link InputStream} can be serialised into a
 * multipart request body with a single parameter.
 *
 * @author Karl Bennett
 */
public class MultipartFormDataBodySerialiser extends InputStreamBodySerialiser<MultipartFormDataContentType> {

    protected MultipartFormDataBodySerialiser() {
        super(MultipartFormDataContentType.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> InputStream serialise(T object) {

        return null;
    }
}
