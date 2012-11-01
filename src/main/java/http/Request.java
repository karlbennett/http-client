package http;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Represents an {@code HTTP} request and can be populated with all the standard request components.
 *
 * @author Karl Bennett
 */
public class Request extends Message<InputStream> {

    /**
     * Generate a new {@code java.net.URL} instance from a {@code java.lang.String} without throwing a checked
     * exception.
     *
     * @param url the url string to use to create the new {@code java.net.URL} instance.
     * @return a new {@code java.net.URL} instance.
     * @throws URLException if an invalid url string is given.
     */
    private static URL quietUrl(String url) {

        try {

            return new URI(url).toURL();

        } catch (MalformedURLException e) {

            throw new URLException(e);

        } catch (URISyntaxException e) {

            throw new URLException(e);
        }
    }


    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     */
    public Request(String url) {

        this(quietUrl(url));
    }

    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code java.net.URL} for the {@code HTTP} server.
     */
    public Request(URL url) {
    }
}
