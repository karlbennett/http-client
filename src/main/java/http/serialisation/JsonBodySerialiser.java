package http.serialisation;

import http.header.JsonContentType;

import java.io.InputStream;

/**
 * A serialiser for {@code JSON}. It can be used to serialise an object or {@link java.util.Map} into JSON.
 *
 * @author Karl Bennett
 */
public class JsonBodySerialiser extends InputStreamBodySerialiser<JsonContentType> {

    protected JsonBodySerialiser() {
        super(JsonContentType.class);
    }

    @Override
    public <T> InputStream serialise(T object) {

        return null;
    }
}
