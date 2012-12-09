package http.serialisation;

import http.header.JsonContentType;

import java.io.InputStream;

/**
 * A deserialiser for {@code JSON}. It can be used to deserialise a request containing JSON into an object that contains
 * the same structure as the {@code JSON} or a {@link java.util.Map}.
 *
 * @author Karl Bennett
 */
public class JsonBodyDeserialiser extends InputStreamBodyDeserialiser<JsonContentType> {

    protected JsonBodyDeserialiser() {
        super(JsonContentType.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> T deserialise(InputStream input, Class<T> type) {

        return null;
    }
}
