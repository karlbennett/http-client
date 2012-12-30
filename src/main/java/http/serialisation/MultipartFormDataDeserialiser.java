package http.serialisation;

import java.io.InputStream;

import static http.util.MimeTypes.*;

/**
 * A deserialiser for multipart form data. It can be used to deserialise an {@link InputStream} that contains multiple
 * parameters into an object or {@link java.util.Map}.
 * <p/>
 * Also if the multipart data only contains a single parameter it is
 * possible to user a {@link String} or {@link InputStream} as the deserialise object type, which will then contains the
 * raw data within the message minus any of the surrounding protocol data.
 *
 * @author Karl Bennett
 */
public class MultipartFormDataDeserialiser extends InputStreamDeserialiser {

    protected MultipartFormDataDeserialiser() {
        super(quietMimeType(MULTIPART, FORM_DATA));
    }

    /**
     * Deserialise the {@code multipart/form-data} data in the supplied input stream into an instance of
     * the requested type.
     *
     * @param input the input to be deserialise.
     * @param type  the run time type of object to deserialise to.
     * @param <T>   the generic type of the object to deserialise to.
     * @return the deserialised object instance.
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
