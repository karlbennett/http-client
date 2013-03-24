package http.header;

import org.junit.Test;

import static http.header.ContentType.CONTENT_TYPE;
import static http.util.MimeTypes.APPLICATION;
import static http.util.MimeTypes.APPLICATION_JSON;
import static http.util.MimeTypes.JSON;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class ContentTypeTest {

    @Test
    public void testCreateAccept() throws Exception {

        ContentType contentType = new ContentType(APPLICATION_JSON);

        assertContentType(contentType);
    }

    @Test
    public void testCreateAcceptWithPrimaryAndSubStrings() throws Exception {

        ContentType contentType = new ContentType(APPLICATION, JSON);

        assertContentType(contentType);
    }

    @Test
    public void testCreateAcceptWithMimeTypeString() throws Exception {

        ContentType contentType = new ContentType(APPLICATION + '/' + JSON);

        assertContentType(contentType);
    }

    @Test
    public void testCreateAcceptWithCopyConstructor() throws Exception {

        ContentType contentType = new ContentType(APPLICATION_JSON);

        ContentType contentTypeCopy = new ContentType(contentType);

        assertEquals("the copy contentType should equal the original.", contentType, contentTypeCopy);

        assertContentType(contentTypeCopy);
    }

    @Test
    public void testCreateAcceptWithConversion() throws Exception {

        Header header = new Header<Object>(CONTENT_TYPE, APPLICATION + '/' + JSON);

        ContentType contentType = new ContentType(header);

        assertContentType(contentType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAcceptWithInvalidPrimaryAndSubStrings() throws Exception {

        new ContentType("te$t", ":te$t");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAcceptWithInvalidMimeTypeString() throws Exception {

        new ContentType("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAcceptWithInvalidHeaderType() throws Exception {

        new ContentType(new Header<Object>("Test", new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAcceptWithInvalidHeaderValue() throws Exception {

        new ContentType(new Header<Object>("Test", new Object()));
    }


    private static void assertContentType(ContentType contentType) {

        assertEquals("the contentType name should be correct.", CONTENT_TYPE, contentType.getName());
        assertEquals("the contentType value should be correct.", APPLICATION_JSON, contentType.getValue());
    }
}
