package http.serialisation;

import javax.activation.MimeType;
import java.io.InputStream;

/**
 * An abstract class that sets the output type for any child serialisers to be an {@link InputStream}.
 *
 * @author Karl Bennett
 */
public abstract class InputStreamSerialiser extends Serialiser<InputStream> {

    protected InputStreamSerialiser(MimeType mimeType) {
        super(InputStream.class, mimeType);
    }
}
