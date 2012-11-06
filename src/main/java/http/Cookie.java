package http;

import java.net.URI;
import java.util.Collection;
import java.util.Date;

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
     * @return the date that the cookie expires.
     */
    public Date getExpires() {

        return null;
    }

    /**
     * Set the expiry date for the cookie with a {@link String}.
     *
     * @param expires a {@code String} containing the cookies expiry date. This must be in the format
     *                "E',' dd MMM yyyy HH':'mm':'ss z".
     * @throws http.date.DateParseException if the expires string is malformed.
     */
    public void setExpires(String expires) {

    }

    /**
     * Set the expiry date for the cookie.
     *
     * @param expires the cookies expiry date.
     */
    public void setExpires(Date expires) {

    }

    /**
     * @return true if the cookie has expired, otherwise false.
     */
    public boolean hasExpired() {

        return false;
    }

    /**
     * @return the max age of the cookie.
     */
    public int getMaxAge() {

        return 0;
    }

    /**
     * Set the maximum age of the cookie as the number of seconds from the current time stamp.
     *
     * @param expiry how long till the cookie expires.
     */
    public void setMaxAge(int expiry) {

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
     * @param value the value of a {@code Cookie} header that will be parsed into one or more cookies.
     * @return a collection of all the cookies contained within the header.
     * @throws IllegalArgumentException if the supplied header is not a {@code Cookie} header.
     */
    public static Collection<Cookie> parse(String value) {

        return null;
    }
}
