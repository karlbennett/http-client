package http.conversion;

/**
 * @author Karl Bennett
 */
public class LongToStringConversionTest extends AbstractConversionToStringTest<Long> {

    public LongToStringConversionTest() {
        super(new LongToStringConversion(), "1", 1L);
    }
}
