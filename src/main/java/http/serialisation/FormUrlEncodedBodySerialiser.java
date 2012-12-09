package http.serialisation;

import http.header.FormUrlEncodedContentType;

import java.io.InputStream;

/**
 * A serialiser for {@code URL} encoded form data. It can be used to serialise an object or map into a URL encoded form
 * request body with parameters that match the attributes within the supplied object or the keys and values within a
 * supplied {@link java.util.Map}.
 *
 * @author Karl Bennett
 */
public class FormUrlEncodedBodySerialiser extends InputStreamBodySerialiser<FormUrlEncodedContentType> {

    protected FormUrlEncodedBodySerialiser() {
        super(FormUrlEncodedContentType.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> InputStream serialise(T object) {

        return null;
    }
}
