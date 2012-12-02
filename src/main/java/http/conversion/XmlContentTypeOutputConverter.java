package http.conversion;

import http.header.XmlContentType;

import java.io.InputStream;

/**
 * @author Karl Bennett
 */
public class XmlContentTypeOutputConverter extends InputStreamContentTypeOutputConverter<XmlContentType> {

    protected XmlContentTypeOutputConverter() {
        super(XmlContentType.class);
    }

    @Override
    public <T> T convert(Class<T> type, InputStream input) {

        return null;
    }
}
