package http.conversion;

/**
 * @author Karl Bennett
 */
public class StringToIntegerConversionTest extends AbstractConversionStringToNumberTest<Integer> {

    public StringToIntegerConversionTest() {
        super(new StringToIntegerConversion(), 1, "1");
    }
}
