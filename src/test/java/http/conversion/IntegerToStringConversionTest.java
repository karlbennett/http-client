package http.conversion;

/**
 * @author Karl Bennett
 */
public class IntegerToStringConversionTest extends AbstractConversionToStringTest<Integer>  {

    public IntegerToStringConversionTest() {
        super(new IntegerToStringConversion(), "1", 1);
    }
}
