package http.serialisation;

import http.header.FormUrlEncodedContentType;

import java.io.InputStream;

/**
 * A deserialiser for {@code URL} encoded form data. It can be used to deserialise a URL encoded form request into an
 * object with attributes that match the included parameters or {@link java.util.Map} that will contain the key value
 * pairs.
 *
 * @author Karl Bennett
 */
public class FormUrlEncodedBodyDeserialiser extends InputStreamBodyDeserialiser<FormUrlEncodedContentType> {

    protected FormUrlEncodedBodyDeserialiser() {
        super(FormUrlEncodedContentType.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> T deserialise(Class<T> type, InputStream input) {

        return null;
    }
}
