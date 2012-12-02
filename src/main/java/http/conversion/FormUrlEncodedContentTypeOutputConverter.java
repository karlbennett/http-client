package http.conversion;

import http.header.FormUrlEncodedContentType;

import java.io.InputStream;

/**
 * @author Karl Bennett
 */
public class FormUrlEncodedContentTypeOutputConverter extends InputStreamContentTypeOutputConverter<FormUrlEncodedContentType> {

    protected FormUrlEncodedContentTypeOutputConverter() {
        super(FormUrlEncodedContentType.class);
    }

    @Override
    public <T> T convert(Class<T> type, InputStream input) {

        return null;
    }
}
