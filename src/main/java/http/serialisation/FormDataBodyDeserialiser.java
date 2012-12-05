package http.serialisation;

import http.header.FormDataContentType;

import java.io.File;
import java.io.InputStream;

/**
 * A deserialiser for multipart form data. It can be used to deserialise a multipart request body that contains multiple
 * parameters into an object or {@link java.util.Map} or a multipart request with a single parameter into a
 * {@link String} or {@link InputStream}.
 *
 * @author Karl Bennett
 */
public class FormDataBodyDeserialiser extends InputStreamBodyDeserialiser<FormDataContentType> {

    protected FormDataBodyDeserialiser() {
        super(FormDataContentType.class);
    }

    @Override
    public <T> T deserialise(Class<T> type, InputStream input) {

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

    public File convertToFile(InputStream input) {

        return null;
    }
}
