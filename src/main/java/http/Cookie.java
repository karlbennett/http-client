package http;

import java.util.Collection;

/**
 * @author Karl Bennett
 */
public class Cookie {

    public Cookie(String name, String value) {

    }

    public void setComment(String purpose) {

    }

    public String getComment() {

        return null;
    }

    public void setCommentURL(String purpose) {

    }

    public String getCommentURL() {

        return null;
    }

    public void setDiscard(boolean discard) {

    }

    public boolean getDiscard() {

        return false;
    }

    public void setDomain(String pattern) {

    }

    public String getDomain() {

        return null;
    }

    public void setMaxAge(long expiry) {

    }

    public long getMaxAge() {

        return 0L;
    }

    public void setPath(String uri) {

    }

    public String getPath() {

        return null;
    }

    public void setSecure(boolean flag) {

    }

    public boolean getSecure() {

        return false;
    }

    public String getName() {

        return null;
    }

    public String getValue() {

        return null;
    }

    public int getVersion() {

        return 0;
    }

    public void setVersion(int v) {

    }

    public void setPortlist(String ports) {

    }

    public String getPortlist() {

        return null;
    }

    public boolean hasExpired() {

        return false;
    }

    public void setValue(String newValue) {

    }

    public static Collection<Cookie> parse(Header header) {

        return null;
    }
}
