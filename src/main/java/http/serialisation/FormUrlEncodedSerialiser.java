package http.serialisation;

import java.io.InputStream;

import static http.util.MimeTypes.*;

/**
 * A serialiser for {@code URL} encoded form data. It can be used to serialise an object or map into a URL encoded form
 * request body with parameters that match the attributes within the supplied object or the keys and values within a
 * supplied {@link java.util.Map}.
 *
 * @author Karl Bennett
 */
public class FormUrlEncodedSerialiser extends InputStreamSerialiser {

    protected FormUrlEncodedSerialiser() {
        super(quietMimeType(APPLICATION, X_WWW_FORM_URL_ENCODED));
    }

    /**
     * Serialise the supplied object into an {@link InputStream} contain {@code application/x-www-form-urlencoded} data.
     *
     * @param object the object to be serialised.
     * @param <T>    the generic type of the object to be serialised.
     * @return an {@code InputStream} containing the serialised object.
     */
    @Override
    public <T> InputStream serialise(T object) {

        return null;
    }
}
