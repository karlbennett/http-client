package http.conversion;

/**
 * Conversion that can be used to convert a {@link String} into an {@link Integer}.
 *
 * @author Karl Bennett
 */
public class StringToIntegerConversion extends Conversion<String, Integer> {

    /**
     * Create a new {@code StringToIntegerConversion}.
     */
    public StringToIntegerConversion() {
        super(String.class, Integer.class);
    }

    /**
     * Convert a {@code String} into an {@code Integer}.
     *
     * @param input the {@code String} representation of an {@code Integer} that is to be converted.
     * @return the {@code Integer} value of the input {@code String}.
     * @throws NumberFormatException if the input {@code String} does not represent a true {@code Integer}.
     */
    @Override
    public Integer convert(String input) {

        return new Integer(input);
    }
}
