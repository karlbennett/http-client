package http.serialisation;

import http.header.XmlContentType;

import java.io.InputStream;

/**
 * A serialiser for {@code XML}. It can be used to serialise an object or {@link java.util.Map} into {@code XML}.
 *
 * @author Karl Bennett
 */
public class XmlBodySerialiser extends InputStreamBodySerialiser<XmlContentType> {

    protected XmlBodySerialiser() {
        super(XmlContentType.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> InputStream serialise(T object) {

        return null;
    }
}
