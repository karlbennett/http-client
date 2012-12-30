package http.conversion;

/**
 * Conversion that can be used to convert a {@link Boolean} into a {@link String}.
 *
 * @author Karl Bennett
 */
public class BooleanToStringConversion extends Conversion<Boolean, String> {

    /**
     * Create a new {@code BooleanToStringConversion}.
     */
    public BooleanToStringConversion() {
        super(Boolean.class, String.class);
    }

    /**
     * Convert a {@code Boolean} into a {@code String}.
     *
     * @param input the {@code Boolean} that will be converted.
     * @return the {@code String} "true" if the {@code Boolean} was {@code true}, otherwise the {@code String} "false"
     *         or an empty {@code String} if the input is {@code null}.
     */
    @Override
    public String convert(Boolean input) {

        if (null == input) return "";

        return input.toString();
    }
}
