package http.serialisation;

import http.header.MultipartFormDataContentType;

import java.io.InputStream;

/**
 * A deserialiser for multipart form data. It can be used to deserialise a multipart request body that contains multiple
 * parameters into an object or {@link java.util.Map} or a multipart request with a single parameter into a
 * {@link String} or {@link InputStream}.
 *
 * @author Karl Bennett
 */
public class MultipartFormDataBodyDeserialiser extends InputStreamBodyDeserialiser<MultipartFormDataContentType> {

    protected MultipartFormDataBodyDeserialiser() {
        super(MultipartFormDataContentType.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> T deserialise(InputStream input, Class<T> type) {

        return null;
    }

    public InputStream convertToInputStream(InputStream input) {

        return null;
    }

    public byte[] convertToByteArray(InputStream input) {

        return null;
    }

    public String convertToString(InputStream input) {

        return null;
    }
}
