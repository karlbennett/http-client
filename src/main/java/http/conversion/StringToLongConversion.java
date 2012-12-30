package http.conversion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Conversion that can be used to convert a {@link String} into a {@link Long}.
 *
 * @author Karl Bennett
 */
public class StringToLongConversion extends Conversion<String, Long> {

    private static final Pattern LONG_PATTERN = Pattern.compile("(\\d+)(?:L|l)");

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

        Matcher matcher = LONG_PATTERN.matcher(input);

        if (matcher.matches()) return new Long(matcher.group(1));

        return new Long(input);
    }
}
