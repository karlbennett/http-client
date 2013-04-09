package http.header;

import org.junit.Test;

import javax.activation.MimeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Abstract test class that contains the common tests for MIME type headers.
 *
 * @author Karl Bennett
 */
public abstract class AbstractMimeTypeTest<H extends Header> {

    private final String name;
    private final MimeType mimeType;


    protected AbstractMimeTypeTest(String name, MimeType mimeType) {

        this.name = name;
        this.mimeType = mimeType;
    }


    /**
     * Create a MIME type header with a {@code MimeType}.
     *
     * @param mimeType the {@code MimeType} to use to create the header.
     * @return the new {@code MimeType} header.
     */
    protected abstract H createMimeTypeHeader(MimeType mimeType);

    /**
     * Create a MIME type header with a primary and sub MIME type value.
     *
     * @param primary the primary MIME type value to use.
     * @param sub     the sub MIME type value to use.
     * @return the new {@code MimeType} header.
     */
    protected abstract H createMimeTypeHeader(String primary, String sub);

    /**
     * Create a MIME type header with a {@code String} representation fo a MIME type.
     *
     * @param rawData a {@code String} that contains a complete MIME type value e.g. "application/json"
     * @return the new {@code MimeType} header.
     */
    protected abstract H createMimeTypeHeader(String rawData);

    /**
     * Copy the supplied header.
     *
     * @param header the header to copy.
     * @return the copied header.
     */
    protected abstract H copyMimeTypeHeader(H header);

    /**
     * Convert the supplied header into the required header subtype.
     *
     * @param header the header to convert.
     * @return the converted header.
     */
    protected abstract H convertMimeTypeHeader(Header header);


    @Test
    public void testCreateAccept() throws Exception {

        H header = createMimeTypeHeader(mimeType);

        assertMimeTypeHeader(header);
    }

    @Test
    public void testCreateAcceptWithPrimaryAndSubStrings() throws Exception {

        H header = createMimeTypeHeader(mimeType.getPrimaryType(), mimeType.getSubType());

        assertMimeTypeHeader(header);
    }

    @Test
    public void testCreateAcceptWithMimeTypeString() throws Exception {

        H header = createMimeTypeHeader(mimeType.toString());

        assertMimeTypeHeader(header);
    }

    @Test
    public void testCreateAcceptWithCopyConstructor() throws Exception {

        H header = createMimeTypeHeader(mimeType);

        H headerCopy = copyMimeTypeHeader(header);

        assertEquals("the copy " + header.getClass().getName() + " should equal the original.", header, headerCopy);

        assertMimeTypeHeader(headerCopy);
    }

    @Test
    public void testCreateAcceptWithConversion() throws Exception {

        Header header = new Header<Object>(name, mimeType.toString());

        H convertedHeader = convertMimeTypeHeader(header);

        assertMimeTypeHeader(convertedHeader);
    }

    @Test
    public void testCreateAcceptWithUnnecessaryConversion() throws Exception {

        Header header = createMimeTypeHeader(mimeType);

        H convertedHeader = convertMimeTypeHeader(header);

        assertMimeTypeHeader(convertedHeader);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAcceptWithInvalidPrimaryAndSubStrings() throws Exception {

        createMimeTypeHeader("te$t", ":te$t");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAcceptWithInvalidMimeTypeString() throws Exception {

        createMimeTypeHeader("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAcceptWithInvalidHeaderType() throws Exception {

        convertMimeTypeHeader(new Header<Object>("Test", new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAcceptWithInvalidHeaderValue() throws Exception {

        convertMimeTypeHeader(new Header<Object>("Test", new Object()));
    }


    private void assertMimeTypeHeader(H header) {

        Class type = header.getClass();

        assertEquals("the " + type.getSimpleName() + " name should be correct.", name, header.getName());
        assertEquals("the " + type.getSimpleName() + " value should be correct.", mimeType, header.getValue());
    }
}
