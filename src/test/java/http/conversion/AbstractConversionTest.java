package http.conversion;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is an abstract class that is implemented to test the generic conversion behaviour of the different
 * {@link Conversion}s.
 *
 * @author Karl Bennett
 */
public abstract class AbstractConversionTest<I, O> {

    private Conversion<I, O> conversion;
    private I input;
    private O output;


    /**
     * Create a new {@code AbstractConversionTest} that will use the supplied conversion, expected output, and input
     * objects.
     *
     * @param conversion the conversion instance to use to carry out the conversion.
     * @param output     the expected output from the conversion.
     * @param input      the input to be converted.
     */
    protected AbstractConversionTest(Conversion<I, O> conversion, O output, I input) {

        this.conversion = conversion;
        this.input = input;
        this.output = output;
    }

    @Test
    public void testConvert() throws Exception {

        assertEquals("the input should be converted to the correct output.", output, conversion.convert(input));
    }
}
