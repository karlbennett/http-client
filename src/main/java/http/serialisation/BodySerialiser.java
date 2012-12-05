package http.serialisation;

import http.header.ContentType;

/**
 * Serialise the an object into the specified {@code Content-Type} help within the specified output type.
 *
 * The {@code Content-Type} will usually be something like {@code application/json}, {@code application/xml}, or
 * {@code application/x-www-form-urlencoded}. Where as the output type will be some form of {@link java.io.InputStream}
 * or {@link CharSequence}.
 *
 * @param <O>   the type of output the {@code BodyDeserialiser} produces.
 * @param <C>   the type of {@code Content-Type} the {@code BodyDeserialiser} produces. This should be set using a typed
 *              {@link http.Header} like {@link http.header.JsonContentType} or {@link http.header.XmlContentType}.
 *
 * @author Karl Bennett
 */
public abstract class BodySerialiser<O, C extends ContentType> {

    private final Class<O> outputType;
    private final Class<C> contentType;


    /**
     * Create a new {@code BodySerialiser} that the supplied content type and produces the supplied output.
     *
     * @param outputType the type of output that the {@code BodySerialiser} produces.
     * @param contentType the type of content that will be contained within the output.
     */
    protected BodySerialiser(Class<O> outputType, Class<C> contentType) {

        this.outputType = outputType;
        this.contentType = contentType;
    }


    /**
     * Serialise the supplied input into the required type.
     *
     * @param object the object to be serialised.
     * @param <T> the type of the object to serialise.
     * @return the serialised object in the container type.
     */
    public abstract <T> O serialise(T object);


    /**
     * The type of the container object that will hold the serialised object data.
     *
     * @return the serialised container type.
     */
    public Class<O> getOutputType() {

        return outputType;
    }

    /**
     * Get the content type that the object will be serialised into e.g. {@code application/json},
     * {@code application/xml}, or {@code application/x-www-form-urlencoded}.
     *
     * @return the serialised content type.
     */
    public Class<C> getContentType() {

        return contentType;
    }
}
