package http.conversion;

import static http.util.Assert.assertNotNull;

/**
 * An interface for converting one type of object into another.
 *
 * @author Karl Bennett
 */
public abstract class Conversion<I, O> {

    private Class<I> inputType;
    private Class<O> outputType;


    /**
     * Create a new {@code Conversion}
     *
     * @param inputType  the type of input that this conversion supports.
     * @param outputType the type of output that this conversion supports.
     */
    protected Conversion(Class<I> inputType, Class<O> outputType) {

        assertNotNull("inputType", inputType);
        assertNotNull("outputType", outputType);

        this.inputType = inputType;
        this.outputType = outputType;
    }

    /**
     * Convert the input object into the output object.
     *
     * @param input the object the be converted.
     * @return the converted object.
     */
    public abstract O convert(I input);


    /**
     * Get the type of input that this conversion supports.
     *
     * @return the input types {@link Class} object.
     */
    public Class<I> getInputType() {
        return inputType;
    }

    /**
     * Get the type of output that this conversion supports.
     *
     * @return the output types {@link Class} object.
     */
    public Class<O> getOutputType() {
        return outputType;
    }
}
