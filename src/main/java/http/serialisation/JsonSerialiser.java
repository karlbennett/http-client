package http.serialisation;

import java.io.InputStream;

import static http.util.MimeTypes.*;

/**
 * A serialiser for {@code JSON}. It can be used to serialise an object or {@link java.util.Map} into JSON.
 *
 * @author Karl Bennett
 */
public class JsonSerialiser extends InputStreamSerialiser {

    protected JsonSerialiser() {
        super(quietMimeType(APPLICATION, JSON));
    }

    /**
     * Serialise the supplied object into an {@link InputStream} contain {@code application/json} data.
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
