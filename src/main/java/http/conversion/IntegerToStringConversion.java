package http.conversion;

/**
 * Conversion that can be used to convert a {@link Integer} into a {@link String}.
 *
 * @author Karl Bennett
 */
public class IntegerToStringConversion extends Conversion<Integer, String> {

    /**
     * Create a new {@code IntegerToStringConversion}.
     */
    public IntegerToStringConversion() {
        super(Integer.class, String.class);
    }

    /**
     * Convert a {@code Integer} into a {@code String}.
     *
     * @param input the {@code Integer} that will be converted.
     * @return the {@code String} representation of the {@code Integer} or an empty {@code String} if the input is
     *         {@code null}.
     */
    @Override
    public String convert(Integer input) {

        if (null == input) return "";

        return input.toString();
    }
}
