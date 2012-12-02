package http.conversion;

import http.header.ContentType;

/**
 * A converter that can produce dynamic output types for a fixed type of input and content. The output type is selected
 * at runtime by providing the {@link Class} of the output type to the
 * {@link ContentTypeOutputConverter#convert(Class, Object)} method.
 *
 * @param <I> the type of input the {@code ContentTypeOutputConverter} accepts.
 *
 * @author Karl Bennett
 */
public abstract class ContentTypeOutputConverter<I, C extends ContentType> {

    private final Class<I> inputType;
    private final Class<C> contentType;


    /**
     * Create a new {@code ContentTypeOutputConverter} that accepts the supplied input and content types.
     *
     * @param inputType the type of input that the {@code ContentTypeOutputConverter} accepts.
     * @param contentType the type of content that should be contained within the input.
     */
    protected ContentTypeOutputConverter(Class<I> inputType, Class<C> contentType) {

        this.inputType = inputType;
        this.contentType = contentType;
    }


    /**
     * Convert the supplied input into the required type.
     *
     * @param type the type of object that will be output after conversion.
     * @param input the input to be converted.
     * @param <T> the output type.
     * @return the converted input.
     */
    public abstract <T> T convert(Class<T> type, I input);


    /**
     * Get the input type that this converter accepts.
     *
     * @return the supported input type.
     */
    public Class<I> getInputType() {

        return inputType;
    }

    /**
     * Get the content type that this converter accepts.
     *
     * @return the supported content type.
     */
    public Class<C> getContentType() {

        return contentType;
    }
}
