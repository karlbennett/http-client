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
     * Deserialise the {@code application/x-www-form-urlencoded} data in the supplied input stream into an instance of
     * the requested type.
     *
     * @param input the input to be deserialise.
     * @param type  the run time type of object to deserialise to.
     * @param <T>   the generic type of the object to deserialise to.
     * @return the deserialised object instance.
     */
    @Override
    public <T> T deserialise(InputStream input, Class<T> type) {

        return null;
    }
}
