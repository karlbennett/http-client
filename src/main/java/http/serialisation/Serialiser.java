package http.serialisation;

import javax.activation.MimeType;

/**
 * Serialise the an object into the specified {@link MimeType} help within the specified output type.
 * <p/>
 * Common {@code MimeType}'s are {@code application/json}, {@code application/xml}, or
 * {@code application/x-www-form-urlencoded}. Output types are generally {@link java.io.InputStream}'s or
 * {@link CharSequence}'s.
 *
 * @param <O> the type of output the {@code Serialiser} produces.
 * @author Karl Bennett
 */
public abstract class Serialiser<O> {

    private final Class<O> outputType;
    private final MimeType mimeType;


    /**
     * Create a new {@code Serialiser} that produces the supplied output type that contains data in the supplied mime
     * type.
     *
     * @param outputType the type of output type that the {@code Serialiser} produces.
     * @param mimeType   the type of content that will be contained within the output.
     */
    protected Serialiser(Class<O> outputType, MimeType mimeType) {

        this.outputType = outputType;
        this.mimeType = mimeType;
    }


    /**
     * Serialise the supplied input into the required type.
     *
     * @param object the object to be serialised.
     * @param <T>    the type of the object to serialise.
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
     * Get the mime type that the object will be serialised into e.g. {@code application/json},
     * {@code application/xml}, or {@code application/x-www-form-urlencoded}.
     *
     * @return the serialised content type.
     */
    public MimeType getMimeType() {

        return mimeType;
    }
}
