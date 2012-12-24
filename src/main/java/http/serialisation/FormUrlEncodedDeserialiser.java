package http.serialisation;

import java.io.InputStream;

import static http.util.MimeTypes.*;

/**
 * A deserialiser for {@code URL} encoded form data. It can be used to deserialise a URL encoded form request into an
 * object with attributes that match the included parameters or {@link java.util.Map} that will populated with the
 * key/value pairs.
 *
 * @author Karl Bennett
 */
public class FormUrlEncodedDeserialiser extends InputStreamDeserialiser {

    protected FormUrlEncodedDeserialiser() {
        super(quietMimeType(APPLICATION, X_WWW_FORM_URL_ENCODED));
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> T deserialise(InputStream input, Class<T> type) {

        return null;
    }
}
