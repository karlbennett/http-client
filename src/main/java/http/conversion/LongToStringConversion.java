package http.conversion;

/**
 * Conversion that can be used to convert a {@link Long} into a {@link String}.
 *
 * @author Karl Bennett
 */
public class LongToStringConversion extends Conversion<Long, String> {

    /**
     * Create a new {@code LongToStringConversion}.
     */
    public LongToStringConversion() {
        super(Long.class, String.class);
    }

    /**
     * Convert a {@code Long} into a {@code String}.
     *
     * @param input the {@code Long} that will be converted.
     * @return the {@code String} representation of the {@code Long} or an empty {@code String} if the input is
     *         {@code null}.
     */
    @Override
    public String convert(Long input) {

        if (null == input) return "";

        return input.toString();
    }
}
