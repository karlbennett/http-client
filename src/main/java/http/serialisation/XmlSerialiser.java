package http.serialisation;

import java.io.InputStream;

import static http.util.MimeTypes.*;

/**
 * A serialiser for {@code XML}. It can be used to serialise an object or {@link java.util.Map} into {@code XML}.
 *
 * @author Karl Bennett
 */
public class XmlSerialiser extends InputStreamSerialiser {

    protected XmlSerialiser() {
        super(quietMimeType(APPLICATION, XML));
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> InputStream serialise(T object) {

        return null;
    }
}
