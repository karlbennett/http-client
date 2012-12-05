package http.serialisation;

import http.header.ContentType;

import java.io.InputStream;

/**
 * An abstract class that sets the input type for any child deserialisers to be an {@link InputStream}.
 *
 * @author Karl Bennett
 */
public abstract class InputStreamBodyDeserialiser<C extends ContentType> extends
        BodyDeserialiser<InputStream, C> {

    protected InputStreamBodyDeserialiser(Class<C> contentType) {
        super(InputStream.class, contentType);
    }
}
