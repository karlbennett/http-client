package http.serialisation;

import java.io.InputStream;

import static http.util.MimeTypes.*;

/**
 * A deserialiser for {@code JSON}. It can be used to deserialise an {@link InputStream} containing JSON into an object
 * that has the same structure as the {@code JSON} or a {@link java.util.Map}.
 *
 * @author Karl Bennett
 */
public class JsonDeserialiser extends InputStreamDeserialiser {

    protected JsonDeserialiser() {
        super(quietMimeType(APPLICATION, JSON));
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> T deserialise(InputStream input, Class<T> type) {

        return null;
    }
}
