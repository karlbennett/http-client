package http.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static http.util.Asserts.assertNotNull;

/**
 * Helper methods for working this {@link URI}s.
 *
 * @author Karl Bennett
 */
public final class URIs {

    private URIs() {
    }


    /**
     * Generate a new {@code java.net.URI} instance from a {@code java.lang.String} without throwing a checked
     * exception.
     *
     * @param uri the uri string to use to create the new {@code java.net.URI} instance.
     * @return a new {@code java.net.URI} instance.
     * @throws URIException if an invalid uri string is given.
     */
    public static URI quietUri(String uri) {

        assertNotNull("uri", uri);

        try {

            return new URI(uri);

        } catch (URISyntaxException e) {

            throw new URIException(e);
        }
    }

    /**
     * Generate a new {@code java.net.URL} instance from a {@code java.lang.String} without throwing a checked
     * exception.
     *
     * @param url the url string to use to create the new {@code java.net.URL} instance.
     * @return a new {@code java.net.URL} instance.
     * @throws URIException if an invalid url string is given.
     */
    public static URL quietUrl(String url) {

        try {

            return quietUri(url).toURL();

        } catch (MalformedURLException e) {

            throw new URIException(e);

        }
    }
}
