package http.conversion;

/**
 * @author Karl Bennett
 */
public class BooleanToStringConversionTest extends AbstractConversionToStringTest<Boolean> {

    public BooleanToStringConversionTest() {
        super(new BooleanToStringConversion(), "true", true);
    }
}
