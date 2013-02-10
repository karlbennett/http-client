package http.util;

import static http.util.Checks.isNotEmpty;

/**
 * A simple parser that splits a {@link String} by a supplied {@code delimiter} and sequentially exposes the parts
 * through it's abstract {@link #next(String)} method.
 *
 * @author Karl Bennett
 */
public abstract class Parser {

    private final String string;
    private final String delimiter;


    /**
     * Create a new {@code Parser} with the supplied {@code String} that will be split by the supplied
     * {@code delimiter}.
     *
     * @param string the {@code String} to parse.
     * @param delimiter the delimiter to split the {@code String} by.
     */
    public Parser(String string, String delimiter) {

        this.string = string;
        this.delimiter = delimiter;
    }


    /**
     * This method should be implemented to get access to the parsed {@code String} parts. It will be called for each
     * part of the split {@code String}.
     *
     * @param part the next part of the parsed {@code String}.
     */
    protected abstract void next(String part);

    /**
     * Initiate the parsing of the {@code String}.
     */
    public void parse() {

        if (isNotEmpty(string)) for (String part : string.split(delimiter)) next(part);
    }
}
