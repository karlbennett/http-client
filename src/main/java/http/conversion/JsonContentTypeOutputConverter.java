package http.conversion;

import http.header.JsonContentType;

import java.io.InputStream;

/**
 * @author Karl Bennett
 */
public class JsonContentTypeOutputConverter extends InputStreamContentTypeOutputConverter<JsonContentType> {

    protected JsonContentTypeOutputConverter() {
        super(JsonContentType.class);
    }

    @Override
    public <T> T convert(Class<T> type, InputStream input) {

        return null;
    }
}
