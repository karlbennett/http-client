package http.conversion;

import http.header.ContentType;

/**
 * @author Karl Bennett
 */
public abstract class ContentTypeInputConverter<O, C extends ContentType> {

    private final Class<O> outputType;
    private final Class<C> contentType;


    protected ContentTypeInputConverter(Class<O> outputType, Class<C> contentType) {

        this.outputType = outputType;
        this.contentType = contentType;
    }


    public abstract <T> O convert(T output);


    public Class<O> getOutputType() {

        return outputType;
    }

    public Class<C> getContentType() {

        return contentType;
    }
}
