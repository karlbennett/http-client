package http.serialisation;

import javax.activation.MimeType;

/**
 * Deserialise the input that contains content of a specified {@link MimeType} and is help within a specified input
 * type.
 * <p/>
 * Common {@code MimeType}'s are {@code application/json}, {@code application/xml}, or
 * {@code application/x-www-form-urlencoded}. Input types are generally {@link java.io.InputStream}'s or
 * {@link CharSequence}'s.
 *
 * @param <I> the type of input object that the {@code Deserialiser} accepts.
 * @author Karl Bennett
 */
public abstract class Deserialiser<I> {

    private final Class<I> inputType;
    private final MimeType mimeType;


    /**
     * Create a new {@code Deserialiser} that accepts the supplied input type and mime type.
     *
     * @param inputType the type of input that the {@code Deserialiser} accepts.
     * @param mimeType  the type of content that should be contained within the input.
     */
    protected Deserialiser(Class<I> inputType, MimeType mimeType) {

        this.inputType = inputType;
        this.mimeType = mimeType;
    }


    /**
     * Deserialise the supplied input into the required type.
     *
     * @param input the input to be deserialise.
     * @param type  the type of object to deserialise to.
     * @return the deserialise input.
     */
    public abstract <T> T deserialise(I input, Class<T> type);


    /**
     * Get the input type that this converter accepts.
     *
     * @return the supported input type.
     */
    public Class<I> getInputType() {

        return inputType;
    }

    /**
     * Get the mime type that this deserialiser accepts.
     *
     * @return the supported content type.
     */
    public MimeType getMimeType() {

        return mimeType;
    }
}
