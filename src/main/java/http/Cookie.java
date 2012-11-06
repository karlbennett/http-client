package http;

import java.net.URI;
import java.util.Collection;

/**
 * Represents an HTTP cookie, that is a header with the name {@code Cookie} and a value containing the cookie name and
 * value.
 * <p/>
 * Example:
 * Cookie: myCookie=myCookieValue;
 *
 * @author Karl Bennett
 * @see <a href="http://www.ietf.org/rfc/rfc2109.txt">RFC 2109</a>
 */
public class Cookie {

    /**
     * Create a new {@code Cookie} with the supplied name and value.
     *
     * @param name  the name of the cookie.
     * @param value the value of the cookie.
     */
    public Cookie(String name, String value) {

    }

    /**
     * @return the cookies comment.
     */
    public String getComment() {

        return null;
    }

    /**
     * Set the comment for the cookie.
     *
     * @param comment the new cookie comment.
     */
    public void setComment(String comment) {

    }

    /**
     * @return the cookies domain.
     */
    public String getDomain() {

        return null;
    }

    /**
     * Set the cookies domain.
     *
     * @param domain the new cookie domain.
     */
    public void setDomain(String domain) {

    }

    /**
     * @return the max age of the cookie.
     */
    public long getMaxAge() {

        return 0L;
    }

    /**
     * Set the maximum age of the cookie as the number of milliseconds from the current time stamp.
     *
     * @param expiry how long till the cookie expires.
     */
    public void setMaxAge(long expiry) {

    }

    /**
     * @return the name of the cookie.
     */
    public String getName() {

        return null;
    }

    /**
     * @return the cookies path.
     */
    public URI getPath() {

        return null;
    }

    /**
     * Set the path of the cookie.
     *
     * @param uri a {@code String} containing the new cookie path.
     */
    public void setPath(String uri) {

    }

    /**
     * Set the path of the cookie.
     *
     * @param uri the new cookie path.
     */
    public void setPath(URI uri) {

    }

    /**
     * @return true if the cookie is secure otherwise false.
     */
    public boolean isSecure() {

        return false;
    }

    /**
     * Set if this is a secure cookie or not.
     *
     * @param flag true if the cookie is secure otherwise false.
     */
    public void setSecure(boolean flag) {

    }

    /**
     * @return the value of the cookie.
     */
    public String getValue() {

        return null;
    }

    /**
     * @return the version of the cookie.
     */
    public int getVersion() {

        return 0;
    }

    /**
     * Set the version of the cookie.
     *
     * @param version the new cookie version.
     */
    public void setVersion(int version) {

    }

    /**
     * Parse the supplied {@code Cookie} header into the cookies contained within its value.
     *
     * @param header the {@code Cookie} header to parse.
     * @return a collection of all the cookies contained within the header.
     * @throws IllegalArgumentException if the supplied header is not a {@code Cookie} header.
     */
    public static Collection<Cookie> parse(Header header) {

        return null;
    }
}
