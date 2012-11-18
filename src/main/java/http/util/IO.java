package http.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static http.util.Assert.*;

/**
 * IO convenience methods.
 *
 * @author Karl Bennett
 */
public final class IO {

    private IO() {
    }

    /**
     * Read the entire contends of a stream into a {@link String} using the default character encoding.
     *
     * @param input the stream to read.
     * @return the entire contents of the stream as a {@code String}.
     * @throws IOException if there was a problem reading from the input.
     * @throws IllegalArgumentException if the input is null.
     */
    public static String readAll(InputStream input) throws IOException {

        assertNotNull("stream", input);

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder output = new StringBuilder();

        String line;
        while (null != (line = reader.readLine())) {

            output.append(line);
        }

        return output.toString();
    }
}
