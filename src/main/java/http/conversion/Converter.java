package http.conversion;

/**
 * An interface for converting one type of object into another.
 *
 * @author Karl Bennett
 */
public interface Converter<I, O> {

    /**
     * @return the class for the object that will be converted from.
     */
    public Class<I> inputType();

    /**
     * @return the class for the object that will be converted to.
     */
    public Class<O> outputType();

    /**
     * Convert the input object into the output object.
     *
     * @param input the object the be converted.
     * @return the converted object.
     */
    public O convert(I input);
}
