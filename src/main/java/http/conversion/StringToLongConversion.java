package http.conversion;

/**
 * Conversion that can be used to convert a {@link String} into a {@link Long}.
 *
 * @author Karl Bennett
 */
public class StringToLongConversion extends Conversion<String, Long> {

    /**
     * Create a new {@code StringToLongConversion}.
     */
    public StringToLongConversion() {
        super(String.class, Long.class);
    }

    /**
     * Convert a {@code String} into a {@code Long}.
     *
     * @param input the {@code String} representation of a {@code Long} that is to be converted.
     * @return the {@code Long} value of the input {@code String}.
     * @throws NumberFormatException if the input {@code String} does not represent a true {@code Long}.
     */
    @Override
    public Long convert(String input) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
