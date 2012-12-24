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
     * @inheritDoc
     */
    @Override
    public <T> InputStream serialise(T object) {

        return null;
    }
}
