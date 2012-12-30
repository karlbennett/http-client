package http.conversion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class StringToLongConversionTest extends AbstractConversionStringToNumberTest<Long> {

    private Conversion<String, Long> conversion;


    public StringToLongConversionTest() {

        this(new StringToLongConversion());
    }

    private StringToLongConversionTest(Conversion<String, Long> conversion) {
        super(conversion, 1L, "1");

        this.conversion = conversion;
    }

    @Test
    public void testConvert1LString() throws Exception {

        assertEquals("the input of \"1L\" should be converted to 1L.", new Long(1l), conversion.convert("1L"));
    }

    @Test
    public void testConvert1lString() throws Exception {

        assertEquals("the input of \"1l\" should be converted to 1l.", new Long(1l), conversion.convert("1l"));
    }
}
