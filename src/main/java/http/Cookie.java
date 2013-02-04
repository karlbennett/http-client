package http;

import http.attribute.Attribute;
import http.date.DateParseException;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static http.util.Asserts.assertNotNull;
import static http.util.Checks.isNotEmpty;
import static http.util.Checks.isNotNull;
import static http.util.Checks.isNull;
import static http.util.URIs.quietUri;

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
public class Cookie extends Attribute<String> {

    private static final ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("E',' dd MMM yyyy HH':'mm':'ss z");
        }
    };

    public static final String SET_COOKIE = "Set-Cookie";
    public static final String COOKIE = "Cookie";

    public static final String COMMENT = "Comment";
    public static final String DOMAIN = "Domain";
    public static final String EXPIRES = "Expires";
    public static final String MAX_AGE = "Max-Age";
    public static final String PATH = "Path";
    public static final String VERSION = "Version";
    public static final String SECURE = "Secure";

    private static final Pattern COOKIE_REGEX = Pattern.compile(
            "(?i)(?:^|\\s)([^\\s\\(\\)\\[\\]\\{\\}=,\"\"\\\\/?@:;]+)=([^\\s\\(\\)\\[\\]\\{\\}=,\"\"\\\\/?@:;]+)" +
                    "(?:" +
                    /**/";\\s+(" + COMMENT + "=[^\\(\\)\\[\\]\\{\\}=,\"\"\\\\/?@:;]+)|" +
                    /**/";\\s+(" + DOMAIN + "=[^\\s\\(\\)\\[\\]\\{\\}=,\"\"\\\\@:;]+)|" +
                    /**/";\\s+(" + EXPIRES + "=[^\\(\\)\\[\\]\\{\\}=\"\"\\\\/?@;]+)|" +
                    /**/";\\s+(" + MAX_AGE + "=\\d+)|" +
                    /**/";\\s+(" + PATH + "=[^\\s\\(\\)\\[\\]\\{\\}=,\"\"\\\\?@:;]+)|" +
                    /**/";\\s+(" + VERSION + "=[\\d]+)|" +
                    /**/";\\s+(" + SECURE + ")|" +
                    /**/"(this is here to force the Secure capture. Don't know why it's needed.)" +
                    ")*");


    private static Date parseDate(String date) {

        assertNotNull("date", date);

        try {

            return DATE_FORMAT_THREAD_LOCAL.get().parse(date);

        } catch (ParseException e) {

            throw new DateParseException(e);
        }
    }

    private static String formatDate(Date date) {

        return DATE_FORMAT_THREAD_LOCAL.get().format(date);
    }

    /**
     * Parse the supplied {@code Cookie} or {@code Set-Cookie} header into the cookies contained within its value.
     *
     * @param value the value of a {@code Cookie} or {@code Set-Cookie} header that will be parsed into one or more
     *              cookies.
     * @return a collection of all the cookies contained within the header.
     * @throws IllegalArgumentException if the supplied header is not a {@code Cookie} header.
     */
    public static Collection<Cookie> parse(String value) {

        Matcher matcher = COOKIE_REGEX.matcher(value);

        Set<Cookie> cookies = new HashSet<>();

        Cookie cookie;

        while (matcher.find()) {

            cookie = new Cookie(matcher.group(1), matcher.group(2));

            String group;
            for (int i = 3; i < matcher.groupCount(); i++) {

                group = matcher.group(i);

                if (null != group) cookie.setFieldByFragment(group);
            }

            cookies.add(cookie);
        }

        return cookies;
    }


    private String comment;
    private String domain;
    private Date expires;
    private int maxAge;
    private URI path;
    private boolean secure;
    private int version;

    /**
     * Create a new {@code Cookie} with the supplied name and value.
     *
     * @param name  the name of the cookie.
     * @param value the value of the cookie.
     */
    public Cookie(String name, String value) {
        super(name, value);

        this.maxAge = -1; // Initialise maxAge to -1 because 0 indicates an expired value.
    }

    /**
     * @return the cookies comment.
     */
    public String getComment() {

        return comment;
    }

    /**
     * Set the comment for the cookie.
     *
     * @param comment the new cookie comment.
     */
    public void setComment(String comment) {

        this.comment = comment;
    }

    /**
     * @return the cookies domain.
     */
    public String getDomain() {

        return domain;
    }

    /**
     * Set the cookies domain.
     *
     * @param domain the new cookie domain.
     */
    public void setDomain(String domain) {

        this.domain = domain;
    }

    /**
     * @return the date that the cookie expires.
     */
    public Date getExpires() {

        return expires;
    }

    /**
     * Set the expiry date for the cookie with a {@link String}.
     *
     * @param expires a {@code String} containing the cookies expiry date. This must be in the format
     *                "E',' dd MMM yyyy HH':'mm':'ss z".
     * @throws http.date.DateParseException if the expires string is malformed.
     */
    public void setExpires(String expires) {

        this.expires = parseDate(expires);
    }

    /**
     * Set the expiry date for the cookie.
     *
     * @param expires the cookies expiry date.
     */
    public void setExpires(Date expires) {

        this.expires = expires;
    }

    /**
     * @return true if the cookie has expired, otherwise false.
     */
    public boolean hasExpired() {

        return 0 == maxAge || (isNotNull(expires) && new Date().getTime() > expires.getTime());
    }

    /**
     * @return the max age of the cookie.
     */
    public int getMaxAge() {

        return maxAge;
    }

    /**
     * Set the maximum age of the cookie as the number of seconds from the current time stamp.
     *
     * @param maxAge how long till the cookie expires.
     */
    public void setMaxAge(int maxAge) {

        this.maxAge = maxAge;
    }

    /**
     * @return the cookies path.
     */
    public URI getPath() {

        return path;
    }

    /**
     * Set the path of the cookie.
     *
     * @param path a {@code String} containing the new cookie path.
     */
    public void setPath(String path) {

        this.path = isNull(path) ? null : quietUri(path);
    }

    /**
     * Set the path of the cookie.
     *
     * @param path the new cookie path.
     */
    public void setPath(URI path) {

        this.path = path;
    }

    /**
     * @return true if the cookie is secure otherwise false.
     */
    public boolean isSecure() {

        return secure;
    }

    /**
     * Set if this is a secure cookie or not.
     *
     * @param secure true if the cookie is secure otherwise false.
     */
    public void setSecure(boolean secure) {

        this.secure = secure;
    }

    /**
     * @return the version of the cookie.
     */
    public int getVersion() {

        return version;
    }

    /**
     * Set the version of the cookie.
     *
     * @param version the new cookie version.
     */
    public void setVersion(int version) {

        this.version = version;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        if (!super.equals(o)) return false;

        Cookie cookie = (Cookie) o;

        return maxAge == cookie.maxAge && secure == cookie.secure && version == cookie.version &&
                (comment == null ? cookie.comment == null : comment.equals(cookie.comment)) &&
                (domain == null ? cookie.domain == null : domain.equals(cookie.domain)) &&
                (expires == null ? cookie.expires == null : expires.equals(cookie.expires)) &&
                (path == null ? cookie.path == null : path.equals(cookie.path));

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();

        result = 31 * result + (comment != null ? comment.hashCode() : 0);

        result = 31 * result + (domain != null ? domain.hashCode() : 0);

        result = 31 * result + (expires != null ? expires.hashCode() : 0);

        result = 31 * result + maxAge;

        result = 31 * result + (path != null ? path.hashCode() : 0);

        result = 31 * result + (secure ? 1 : 0);

        result = 31 * result + version;

        return result;
    }

    @Override
    public String toString() {

        StringBuilder toStringBuilder = new StringBuilder(getName());

        if (isNotEmpty(getValue())) toStringBuilder.append('=').append(getValue());

        if (isNotNull(comment)) toStringBuilder.append("; ").append(COMMENT).append("=").append(comment);

        if (isNotNull(domain)) toStringBuilder.append("; ").append(DOMAIN).append("=").append(domain);

        if (isNotNull(expires)) toStringBuilder.append("; ").append(EXPIRES).append("=").append(formatDate(expires));

        if (0 < maxAge) toStringBuilder.append("; ").append(MAX_AGE).append("=").append(maxAge);

        if (isNotNull(path)) toStringBuilder.append("; ").append(PATH).append("=").append(path);

        if (0 < version) toStringBuilder.append("; ").append(VERSION).append("=").append(version);

        if (secure) toStringBuilder.append("; ").append(SECURE);

        return toStringBuilder.toString();
    }

    /**
     * A private helper method for setting the field of this cookie from the fragment of a cookie string.
     *
     * Example:
     * <code>
     *      Cookie cookie = new Cookie("name", "value");
     *      cookie.setFieldByFragment("Comment=This is a comment;");
     *      "This is a comment".equals(cookie.getComment()); // true
     * </code>
     *
     * @param fragment the fragment of the cookie string that is going to be used to set a field.
     */
    private void setFieldByFragment(String fragment) {

        assertNotNull("fragment", fragment);

        // Special case for the "Secure" cookie attribute.
        if (SECURE.equals(fragment)) {

            setSecure(true);

            return;
        }

        String[] parts = fragment.split("=");

        if (2 > parts.length) {

            throw new IllegalArgumentException(
                    "The (fragment) variable must have a structure of (\\w+=\\w+) or equal \"Secure\".");
        }

        // Set the appropriate field.
        // Yes this could be done with reflection, but that would be error prone, slow, and over kill.
        if (parts[0].startsWith(COMMENT)) setComment(parts[1]);

        if (parts[0].startsWith(DOMAIN)) setDomain(parts[1]);

        if (parts[0].startsWith(EXPIRES)) setExpires(parts[1]);

        if (parts[0].startsWith(MAX_AGE)) setMaxAge(Integer.parseInt(parts[1]));

        if (parts[0].startsWith(PATH)) setPath(parts[1]);

        if (parts[0].startsWith(VERSION)) setVersion(Integer.parseInt(parts[1]));
    }
}
