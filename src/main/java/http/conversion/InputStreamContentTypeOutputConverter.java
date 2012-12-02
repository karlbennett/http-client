package http.conversion;

import http.header.ContentType;

import java.io.InputStream;

/**
 * @author Karl Bennett
 */
public abstract class InputStreamContentTypeOutputConverter<C extends ContentType> extends
        ContentTypeOutputConverter<InputStream, C> {

    protected InputStreamContentTypeOutputConverter(Class<C> contentType) {
        super(InputStream.class, contentType);
    }
}
