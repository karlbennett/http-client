package http.serialisation;

import http.header.ContentType;

/**
 * @author Karl Bennett
 */
public abstract class BodySerialiser<O, C extends ContentType> {

    private final Class<O> outputType;
    private final Class<C> contentType;


    protected BodySerialiser(Class<O> outputType, Class<C> contentType) {

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
