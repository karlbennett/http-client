package http.conversion;

/**
 * Conversion that can be used to convert a {@link String} into a {@link Boolean}.
 *
 * @author Karl Bennett
 */
public class StringToBooleanConversion extends Conversion<String, Boolean> {

    /**
     * Create a new {@code StringToBooleanConversion}.
     */
    public StringToBooleanConversion() {
        super(String.class, Boolean.class);
    }

    /**
     * Convert a {@code String} into a {@code Boolean}. Will return {@code true} if the supplied {@code String} is equal
     * to "1" or "true" ignoring case. All other {@code String}'s will return {@code false}.
     *
     * @param input the {@code String} to be converted to a {@code Boolean}.
     * @return {@code true} it the {@code String} is equal to "1" or "true" ignoring case, otherwise {@code false}.
     */
    @Override
    public Boolean convert(String input) {

        return "1".equals(input) || Boolean.valueOf(input);
    }
}
