package http.serialisation;

import http.header.XmlContentType;

import java.io.InputStream;

/**
 * A deserialiser for {@code XML}. It can be used to deserialise a request containing JSON into an object that contains
 * the same structure as the {@code XML} or a {@link java.util.Map}.
 *
 * @author Karl Bennett
 */
public class XmlBodyDeserialiser extends InputStreamBodyDeserialiser<XmlContentType> {

    protected XmlBodyDeserialiser() {
        super(XmlContentType.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public <T> T deserialise(Class<T> type, InputStream input) {

        return null;
    }
}
