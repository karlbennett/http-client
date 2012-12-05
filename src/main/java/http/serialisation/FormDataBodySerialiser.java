package http.serialisation;

import http.header.FormDataContentType;

import java.io.InputStream;

/**
 * A serialiser for multipart form data. It can be used to serialise an object or {@link java.util.Map} into a multipart
 * request body that contains multiple parameters. Also a {@link String} or {@link InputStream} can be serialised into a
 * multipart request body with a single parameter.
 *
 * @author Karl Bennett
 */
public class FormDataBodySerialiser extends InputStreamBodySerialiser<FormDataContentType> {

    protected FormDataBodySerialiser() {
        super(FormDataContentType.class);
    }

    @Override
    public <T> InputStream serialise(T object) {

        return null;
    }
}
