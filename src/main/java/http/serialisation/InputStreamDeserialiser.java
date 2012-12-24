package http.serialisation;

import javax.activation.MimeType;
import java.io.InputStream;

/**
 * An abstract class that sets the input type for any child deserialisers to be an {@link InputStream}.
 *
 * @author Karl Bennett
 */
public abstract class InputStreamDeserialiser extends Deserialiser<InputStream> {

    protected InputStreamDeserialiser(MimeType mimeType) {
        super(InputStream.class, mimeType);
    }
}
