package http.conversion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class StringToBooleanConversionTest extends AbstractConversionTest<String, Boolean> {

    private Conversion<String, Boolean> conversion;

    public StringToBooleanConversionTest() {

        this(new StringToBooleanConversion());
    }

    private StringToBooleanConversionTest(Conversion<String, Boolean> conversion) {
        super(conversion, true, "true");

        this.conversion = conversion;
    }

    @Test
    public void testConvert1String() throws Exception {

        assertEquals("the input should be converted to the correct output.", true, conversion.convert("1"));
    }
}
